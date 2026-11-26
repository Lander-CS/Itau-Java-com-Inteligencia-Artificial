package exercicio7;

/*
 * EXERCÍCIO 7 - PARTE 1: A Superclasse (Classe Mãe)
 * 
 * OBJETIVOS:
 * 1. Praticar Herança: centralizar tudo o que é comum a todas as contas do Itaú.
 * 2. Usar o modificador 'protected' para permitir acesso direto pelas classes filhas.
 * 
 * REQUISITOS:
 * 1. Atributos PROTECTED:
 *    - protected String titular;
 *    - protected String numeroConta;
 *    - protected double saldo;
 * 
 * 2. Construtor:
 *    - Recebe titular, numeroConta e saldoInicial.
 * 
 * 3. Métodos:
 *    - depositar(double valor): soma ao saldo se valor > 0.
 *    - sacar(double valor): saque padrão (sem taxa e sem cheque especial):
 *        - Se valor > 0 e valor <= saldo: subtrai e exibe sucesso.
 *        - Senão: exibe "Saldo insuficiente!".
 *    - exibirExtrato(): imprime Titular, Conta e Saldo formatado.
 *    - getSaldo(), getTitular(), getNumeroConta().
 */
public class ContaBancaria {
    // 1. Declare os atributos protected aqui:
    protected String titular;
    protected String numeroConta;
    protected double saldo;

    // 2. Crie o construtor aqui:
    public ContaBancaria(String titular, String numeroConta, double saldoInicial) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
    }

    // 3. Implemente os métodos comuns aqui:
    public void sacar(double valor) {
        if (valor <= saldo && valor > 0) {
            saldo -= valor;
            System.out.println("Saque de" + valor + "realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente!");
        }

    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Deposito de " + valor + "realizado com sucesso!");
        } else {
            System.out.println("Valor de deposito invalido!");
        }
    }

    public void exibirExtrato() {
        System.out.println("Titular: " + titular);
        System.out.println("Conta: " + numeroConta);
        System.out.println("Saldo: " + saldo);
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

}
