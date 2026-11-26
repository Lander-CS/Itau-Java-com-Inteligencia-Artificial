package exercicio8;

/**
 * INTERFACE TRADICIONAL (CONTRATO)
 * 
 * Uma interface não define atributos de instância nem implementações de métodos.
 * Ela serve como uma especificação: qualquer classe que assinar este contrato
 * com a palavra 'implements' É OBRIGADA a fornecer o cálculo do seu tributo.
 */
public interface Tributavel {
    // Por padrão em interfaces, métodos são public e abstract (apenas a assinatura):
    double calcularTributo();
}
