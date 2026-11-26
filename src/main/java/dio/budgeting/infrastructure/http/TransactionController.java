package dio.budgeting.infrastructure.http;

import dio.budgeting.application.ListAllTransactionsUseCase;
import dio.budgeting.application.ListTransactionsByCategoryUseCase;
import dio.budgeting.application.PersistTransactionUseCase;
import dio.budgeting.application.SummarizeBudgetUseCase;
import dio.budgeting.application.output.BudgetSummaryOutput;
import dio.budgeting.domain.Category;
import dio.budgeting.infrastructure.http.request.AiTextCommandRequest;
import dio.budgeting.infrastructure.http.request.TransactionRequest;
import dio.budgeting.infrastructure.http.response.AiTextCommandResponse;
import dio.budgeting.infrastructure.http.response.TransactionResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.audio.speech.SpeechModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final PersistTransactionUseCase persistTransactionUseCase;
    private final ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase;
    private final ListAllTransactionsUseCase listAllTransactionsUseCase;
    private final SummarizeBudgetUseCase summarizeBudgetUseCase;

    private final OpenAiAudioTranscriptionModel transcriptionModel;
    private final ChatClient chatClient;
    private final SpeechModel speechModel;

    public TransactionController(PersistTransactionUseCase persistTransactionUseCase,
                                 ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase,
                                 ListAllTransactionsUseCase listAllTransactionsUseCase,
                                 SummarizeBudgetUseCase summarizeBudgetUseCase,
                                 OpenAiAudioTranscriptionModel transcriptionModel,
                                 @Value("classpath:prompts/system-message.st") Resource systemPrompt,
                                 ChatClient.Builder chatClientBuilder,
                                 SpeechModel speechModel) throws IOException {
        this.persistTransactionUseCase = persistTransactionUseCase;
        this.listTransactionsByCategoryUseCase = listTransactionsByCategoryUseCase;
        this.listAllTransactionsUseCase = listAllTransactionsUseCase;
        this.summarizeBudgetUseCase = summarizeBudgetUseCase;
        this.transcriptionModel = transcriptionModel;
        this.chatClient = chatClientBuilder
                .defaultSystem(systemPrompt.getContentAsString(StandardCharsets.UTF_8))
                .defaultTools(
                        persistTransactionUseCase,
                        listTransactionsByCategoryUseCase,
                        listAllTransactionsUseCase,
                        summarizeBudgetUseCase)
                .build();
        this.speechModel = speechModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@RequestBody TransactionRequest request) {
        var transaction = persistTransactionUseCase.execute(request.toInput());
        return TransactionResponse.from(transaction);
    }

    @GetMapping
    public List<TransactionResponse> listAllTransactions() {
        return listAllTransactionsUseCase.execute()
                .stream()
                .map(TransactionResponse::from)
                .toList();
    }

    @GetMapping("/summary")
    public BudgetSummaryOutput getBudgetSummary() {
        return summarizeBudgetUseCase.execute();
    }

    @GetMapping("/{category}")
    public List<TransactionResponse> readTransactions(@PathVariable Category category) {
        return listTransactionsByCategoryUseCase.execute(category)
                .stream()
                .map(TransactionResponse::from)
                .toList();
    }

    /**
     * Fluxo Principal com Áudio:
     * 1. Recebe o arquivo de áudio enviado pelo cliente (MultipartFile)
     * 2. Transcreve o áudio em texto com OpenAiAudioTranscriptionModel (Whisper)
     * 3. Usa o ChatClient com Tool Calling (@Tool) para executar o caso de uso real
     * 4. Converte a resposta final em voz MP3 com SpeechModel (Text-to-Speech)
     */
    @PostMapping(value = "/ai", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "audio/mp3")
    public ResponseEntity<Resource> processVoiceCommand(@RequestParam("file") MultipartFile file) {
        var userMessage = transcriptionModel.call(file.getResource());
        var result = chatClient.prompt().user(userMessage).call().content();

        byte[] audio = speechModel.call(result);
        var resource = new ByteArrayResource(audio);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("audio.mp3")
                                .build()
                                .toString())
                .body(resource);
    }

    /**
     * Evolução: Endpoint de comando em texto para interagir com o mesmo ChatClient e Tool Calling
     * sem precisar gravar um arquivo de áudio.
     */
    @PostMapping("/ai/text")
    public AiTextCommandResponse processTextCommand(@RequestBody AiTextCommandRequest request) {
        if (request == null || request.message() == null || request.message().isBlank()) {
            throw new IllegalArgumentException("O comando de texto para o assistente não pode ser vazio.");
        }
        var result = chatClient.prompt().user(request.message()).call().content();
        return new AiTextCommandResponse(request.message(), result);
    }
}
