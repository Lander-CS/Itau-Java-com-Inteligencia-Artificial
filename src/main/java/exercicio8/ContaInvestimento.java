package exercicio8;

/**
 * CLASSE 1 QUE IMPLEMENTA A INTERFACE 'Tributavel'
 * 
 * Uma Conta de Investimento é um produto bancário financeiro.
 * Regra do Itaú: 2% de tributo/IOF retido sobre o saldo investido.
 */
public class ContaInvestimento implements Tributavel {
    private String titular;
    private double saldoInvestido;

    public ContaInvestimento(String titular, double saldoInvestido) {
        this.titular = titular;
        this.saldoInvestido = saldoInvestido;
    }

    // OBRIGATÓRIO: Cumprindo o contrato da interface Tributavel
    @Override
    public double calcularTributo() {
        return this.saldoInvestido * 0.02; // 2% de imposto
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldoInvestido() {
        return saldoInvestido;
    }
}
