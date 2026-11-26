package dio.budgeting.application.output;

import dio.budgeting.domain.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record TransactionOutput(String id, String description, String category, long amountInCents, double value) {
    public static TransactionOutput from(Transaction transaction) {
        double valueInReais = BigDecimal.valueOf(transaction.getAmount())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                .doubleValue();

        return new TransactionOutput(
                transaction.getId().uuid().toString(),
                transaction.getDescription(),
                transaction.getCategory().name(),
                transaction.getAmount(),
                valueInReais);
    }
}
