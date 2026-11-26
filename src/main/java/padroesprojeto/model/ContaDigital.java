package padroesprojeto.model;

/**
 * Entidade de domínio que representa uma Conta Digital no Itaú.
 */
public class ContaDigital {
    private final String titular;
    private final String numeroConta;
    private double saldo;

    public ContaDigital(String titular, String numeroConta, double saldoInicial) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void debitar(double valor) {
        this.saldo -= valor;
    }

    public void creditar(double valor) {
        this.saldo += valor;
    }
}
