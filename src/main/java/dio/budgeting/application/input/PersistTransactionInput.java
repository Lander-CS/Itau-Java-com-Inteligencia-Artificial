package dio.budgeting.application.input;

import dio.budgeting.domain.Category;
import org.springframework.ai.tool.annotation.ToolParam;

public record PersistTransactionInput(
        @ToolParam(description = "Descrição detalhada do gasto financeiro") String description,
        @ToolParam(description = "Valor do gasto em centavos (ex: R$ 50,00 = 5000)") long amount,
        @ToolParam(description = "Categoria da transação (GROCERIES, PHARMA, AUTO, RESTAURANT, EDUCATION, UTILITIES, ENTERTAINMENT)") Category category) {
}
