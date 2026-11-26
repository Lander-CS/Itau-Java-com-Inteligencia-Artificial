package exercicio7;

/*
 * EXERCÍCIO 7 - PARTE 2: Subclasse ContaCorrente (Herança e Polimorfismo)
 * 
 * OBJETIVOS:
 * 1. Herdar de ContaBancaria usando 'extends ContaBancaria'.
 * 2. Chamar o construtor da mãe usando 'super(...)'.
 * 3. Praticar Polimorfismo: sobrescrever (@Override) o método sacar para cobrar
 *    taxa de operação (R$ 2.50) e permitir uso do limite de cheque especial!
 * 
 * REQUISITOS:
 * 1. Herança:
 *    - public class ContaCorrente extends ContaBancaria
 * 
 * 2. Atributo próprio privado:
 *    - private double limiteChequeEspecial;
 * 
 * 3. Construtor:
 *    - Recebe titular, numeroConta, saldoInicial e limiteChequeEspecial.
 *    - Invoca super(titular, numeroConta, saldoInicial);
 *    - Inicializa this.limiteChequeEspecial.
 * 
 * 4. Polimorfismo no método sacar(double valor):
 *    - Anote com @Override.
 *    - Define uma taxa de saque fixa de R$ 2.50 (double taxa = 2.50;).
 *    - Valor total debitado = valor + taxa.
 *    - Se valorTotal <= (saldo + limiteChequeEspecial):
 *        - saldo -= valorTotal;
 *        - Exibe confirmação com valor do saque, taxa cobrada e novo saldo.
 *    - Senão: exibe "Saldo e limite insuficientes para saque de R$ X (com taxa de R$ 2.50)!".
 * 
 * 5. Sobrescreva (@Override) exibirExtrato():
 *    - Chama super.exibirExtrato() ou imprime os dados incluindo o Limite de Cheque Especial.
 */
public class ContaCorrente extends ContaBancaria {
    // TODO: Implemente os atributos, construtor e métodos polimórficos aqui
    private double limiteChequeEspecial;

    public ContaCorrente(String titular, String numeroConta, double saldoInicial, double limiteChequeEspecial) {
        super(titular, numeroConta, saldoInicial);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public void sacar(double valor) {
        double taxa = 2.50;
        double valorTotal = valor + taxa;
        if (valorTotal <= (saldo + limiteChequeEspecial)) {
            saldo -= valorTotal;
            System.out.println("Saque realizado com sucesso!");
            System.out.println("Valor do saque: R$ " + valor);
            System.out.println("Taxa cobrada: R$ " + taxa);
            System.out.println("Novo saldo: R$ " + saldo);
        } else {
            System.out.println(
                    "Saldo e limite insuficientes para saque de R$ " + valor + " (com taxa de R$ " + taxa + ")!");
        }
    }

    @Override
    public void exibirExtrato() {
        super.exibirExtrato();
        System.out.println("Limite de Cheque Especial: R$ " + limiteChequeEspecial);
    }

}
