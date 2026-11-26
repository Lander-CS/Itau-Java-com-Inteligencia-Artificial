package exercicio6;

/*
 * EXERCÍCIO 6 - PARTE 1: A Classe de Modelo (Molde)
 * 
 * OBJETIVOS:
 * 1. Praticar Abstração: escolher os atributos e comportamentos essenciais de uma Conta Corrente.
 * 2. Praticar Encapsulamento: usar atributos 'private' e métodos 'public'.
 * 3. Criar Construtor para inicializar o objeto.
 * 4. Implementar métodos de negócio com validações (depositar, sacar, exibirExtrato).
 * 
 * REQUISITOS DA CLASSE ContaCorrente:
 * 
 * 1. Atributos PRIVADOS:
 *    - titular (String)
 *    - agencia (String)
 *    - numeroConta (String)
 *    - saldo (double)
 *    - limiteChequeEspecial (double)
 * 
 * 2. Construtor:
 *    - Deve receber titular, agencia, numeroConta, saldoInicial e limiteChequeEspecial.
 *    - Use 'this.atributo = parametro;' para atribuir os valores.
 * 
 * 3. Métodos PÚBLICOS:
 *    - depositar(double valor):
 *        - Se valor > 0: soma ao saldo e exibe mensagem de sucesso.
 *        - Senão: exibe "Valor de depósito inválido!".
 * 
 *    - sacar(double valor):
 *        - O cliente pode sacar usando seu saldo + limiteChequeEspecial!
 *        - Se valor > 0 E valor <= (saldo + limiteChequeEspecial):
 *            - Subtrai valor do saldo (o saldo pode ficar negativo até o valor do limite!)
 *            - Exibe mensagem de sucesso com novo saldo.
 *        - Senão: exibe "Saldo e limite insuficientes para saque de R$ X!".
 * 
 *    - exibirExtrato():
 *        - Exibe com printf: Titular, Agência/Conta, Saldo Atual e Limite do Cheque Especial.
 * 
 *    - Getters:
 *        - getTitular(), getSaldo(), getLimiteChequeEspecial()
 */
public class ContaCorrente {
    // 1. Declare os atributos privados aqui:
    private String titular;
    private String agencia;
    private String numeroConta;
    private double saldo;
    private double limiteChequeEspecial;

    // 2. Crie o construtor aqui:
    public ContaCorrente(String titular, String agencia, String numeroConta, double saldo,
            double limiteChequeEspecial) {
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.limiteChequeEspecial = limiteChequeEspecial;
        this.titular = titular;
    }

    // 3. Implemente os métodos (depositar, sacar, exibirExtrato e getters) aqui:
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.print("Depósito de " + valor + " realizado com sucesso. Saldo atual: " + saldo);
        } else {
            System.out.print("Valor de depósito inválido!");
        }
    }

    public void sacar(double valor) {

        if (valor > 0 && valor <= (saldo + limiteChequeEspecial)) {
            this.saldo -= valor;
            System.out.print("Saque de " + valor + " realizado com sucesso. Saldo atual: " + saldo);
        } else {
            System.out.print("Saldo e limite insuficientes para saque de R$ " + valor + "!");
        }
    }

    public void exibirExtrato() {
        System.out.printf("Titular: " + this.titular + "\n Agência: " + this.agencia + "\n Conta: " + this.numeroConta
                + "\n Saldo: " + this.saldo + "\n Limite do Cheque Especial: " + this.limiteChequeEspecial);
    }

    public String getTitular() {
        return this.titular;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public double getLimiteChequeEspecial() {
        return this.limiteChequeEspecial;
    }
}