package exercicio8;

/**
 * CLASSE 2 QUE IMPLEMENTA A INTERFACE 'Tributavel'
 * 
 * Um Seguro de Vida NÃO é uma conta bancária, é uma apólice de proteção.
 * Mesmo sendo um produto completamente diferente, ambos assinam o mesmo contrato 'Tributavel'!
 * Regra do Itaú: Taxa fixa de R$ 45,00 de IOF sobre a contratação da apólice.
 */
public class SeguroDeVida implements Tributavel {
    private String titular;
    private double valorApolice;

    public SeguroDeVida(String titular, double valorApolice) {
        this.titular = titular;
        this.valorApolice = valorApolice;
    }

    // OBRIGATÓRIO: Cumprindo o contrato da interface Tributavel
    @Override
    public double calcularTributo() {
        return 45.00; // Taxa fixa de imposto
    }

    public String getTitular() {
        return titular;
    }

    public double getValorApolice() {
        return valorApolice;
    }
}
