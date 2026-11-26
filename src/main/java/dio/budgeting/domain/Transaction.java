package dio.budgeting.domain;

/**
 * Entidade de Domínio: Transação Financeira.
 * Inclui validações invariantes de negócio antes de permitir a criação da transação.
 */
public class Transaction {
    private final TransactionId id;
    private final String description;
    private final long amount;
    private final Category category;

    public Transaction(TransactionId id, String description, long amount, Category category) {
        validate(description, amount, category);
        this.id = id != null ? id : new TransactionId();
        this.description = description.trim();
        this.amount = amount;
        this.category = category;
    }

    public Transaction(String description, long amount, Category category) {
        this(new TransactionId(), description, amount, category);
    }

    private static void validate(String description, long amount, Category category) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da transação não pode ser vazia.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor da transação (em centavos) deve ser maior que zero.");
        }
        if (category == null) {
            throw new IllegalArgumentException("A categoria da transação é obrigatória.");
        }
    }

    public TransactionId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public long getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }
}
