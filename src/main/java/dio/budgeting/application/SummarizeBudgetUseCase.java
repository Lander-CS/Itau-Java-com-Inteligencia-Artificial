package dio.budgeting.application;

import dio.budgeting.application.output.BudgetSummaryOutput;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Evolução: Nova ferramenta (@Tool) para o Tool Calling do Spring AI.
 * Permite que a IA responda perguntas analíticas sobre o orçamento total,
 * média de gastos e distribuição por categoria.
 */
@Service
public class SummarizeBudgetUseCase {
    private final TransactionRepository transactionRepository;

    public SummarizeBudgetUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "summarize-budget", description = "Gera um resumo analítico completo do orçamento com total gasto em reais, quantidade de transações, ticket médio e subtotal por categoria")
    public BudgetSummaryOutput execute() {
        List<Transaction> transactions = transactionRepository.findAll();

        long totalCents = transactions.stream()
                .mapToLong(Transaction::getAmount)
                .sum();

        double totalReais = toReais(totalCents);
        double averageReais = transactions.isEmpty()
                ? 0.0
                : toReais(Math.round((double) totalCents / transactions.size()));

        Map<String, Double> byCategory = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getCategory().name(),
                        LinkedHashMap::new,
                        Collectors.collectingAndThen(
                                Collectors.summingLong(Transaction::getAmount),
                                SummarizeBudgetUseCase::toReais
                        )
                ));

        return new BudgetSummaryOutput(
                transactions.size(),
                totalCents,
                totalReais,
                averageReais,
                byCategory
        );
    }

    private static double toReais(long cents) {
        return BigDecimal.valueOf(cents)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
