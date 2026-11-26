package dio.budgeting.application.output;

import java.util.Map;

/**
 * Evolução: Resumo consolidado do orçamento com total de transações,
 * valor total gasto em reais, média por transação e subtotal por categoria.
 */
public record BudgetSummaryOutput(
        int totalTransactions,
        long totalAmountInCents,
        double totalValueInReais,
        double averageValueInReais,
        Map<String, Double> totalByCategoryInReais) {
}
