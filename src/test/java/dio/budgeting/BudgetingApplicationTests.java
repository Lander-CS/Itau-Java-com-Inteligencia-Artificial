package dio.budgeting;

import dio.budgeting.application.ListAllTransactionsUseCase;
import dio.budgeting.application.ListTransactionsByCategoryUseCase;
import dio.budgeting.application.PersistTransactionUseCase;
import dio.budgeting.application.SummarizeBudgetUseCase;
import dio.budgeting.application.input.PersistTransactionInput;
import dio.budgeting.application.output.BudgetSummaryOutput;
import dio.budgeting.application.output.TransactionOutput;
import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class BudgetingApplicationTests {

    @Autowired
    private PersistTransactionUseCase persistTransactionUseCase;

    @Autowired
    private ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase;

    @Autowired
    private ListAllTransactionsUseCase listAllTransactionsUseCase;

    @Autowired
    private SummarizeBudgetUseCase summarizeBudgetUseCase;

    @Test
    @DisplayName("Deve carregar o contexto da aplicação Spring Boot + Spring AI com sucesso")
    void contextLoads() {
        assertNotNull(persistTransactionUseCase);
        assertNotNull(listTransactionsByCategoryUseCase);
        assertNotNull(listAllTransactionsUseCase);
        assertNotNull(summarizeBudgetUseCase);
    }

    @Test
    @DisplayName("Deve persistir transação válida e converter centavos para reais corretamente")
    void shouldPersistAndConvertCentsToReais() {
        PersistTransactionInput input = new PersistTransactionInput("Supermercado Itaú", 15050L, Category.GROCERIES);
        TransactionOutput output = persistTransactionUseCase.execute(input);

        assertNotNull(output.id());
        assertEquals("Supermercado Itaú", output.description());
        assertEquals("GROCERIES", output.category());
        assertEquals(15050L, output.amountInCents());
        assertEquals(150.50, output.value(), 0.001);
    }

    @Test
    @DisplayName("Deve filtrar transações por categoria e gerar resumo consolidado do orçamento")
    void shouldFilterByCategoryAndSummarizeBudget() {
        persistTransactionUseCase.execute(new PersistTransactionInput("Compra Mercado", 10000L, Category.GROCERIES));
        persistTransactionUseCase.execute(new PersistTransactionInput("Farmácia Popular", 5000L, Category.PHARMA));
        persistTransactionUseCase.execute(new PersistTransactionInput("Abastecimento Posto", 20000L, Category.AUTO));

        List<TransactionOutput> groceries = listTransactionsByCategoryUseCase.execute(Category.GROCERIES);
        assertEquals(1, groceries.size());
        assertEquals(100.00, groceries.get(0).value(), 0.001);

        BudgetSummaryOutput summary = summarizeBudgetUseCase.execute();
        assertEquals(3, summary.totalTransactions());
        assertEquals(35000L, summary.totalAmountInCents());
        assertEquals(350.00, summary.totalValueInReais(), 0.001);
        assertTrue(summary.totalByCategoryInReais().containsKey("GROCERIES"));
        assertTrue(summary.totalByCategoryInReais().containsKey("PHARMA"));
        assertTrue(summary.totalByCategoryInReais().containsKey("AUTO"));
    }

    @Test
    @DisplayName("Deve rejeitar transações com descrição vazia, valor inválido ou categoria nula")
    void shouldValidateDomainRulesBeforeSaving() {
        assertThrows(IllegalArgumentException.class,
                () -> new Transaction("", 5000L, Category.GROCERIES));

        assertThrows(IllegalArgumentException.class,
                () -> new Transaction("Gasto inválido", 0L, Category.GROCERIES));

        assertThrows(IllegalArgumentException.class,
                () -> new Transaction("Gasto negativo", -100L, Category.PHARMA));

        assertThrows(IllegalArgumentException.class,
                () -> new Transaction("Sem categoria", 1000L, null));
    }
}
