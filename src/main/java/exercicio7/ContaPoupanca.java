package exercicio7;

/*
 * EXERCÍCIO 7 - PARTE 3: Subclasse ContaPoupanca (Herança e Rendimento)
 * 
 * OBJETIVOS:
 * 1. Herdar de ContaBancaria usando 'extends ContaBancaria'.
 * 2. Chamar o construtor da mãe com 'super(...)'.
 * 3. Adicionar comportamento específico de poupança (render juros).
 * 
 * REQUISITOS:
 * 1. Herança:
 *    - public class ContaPoupanca extends ContaBancaria
 * 
 * 2. Atributo próprio privado:
 *    - private double taxaRendimento; // ex: 0.01 para 1% ao mês
 * 
 * 3. Construtor:
 *    - Recebe titular, numeroConta, saldoInicial e taxaRendimento.
 *    - Invoca super(titular, numeroConta, saldoInicial);
 *    - Inicializa this.taxaRendimento.
 * 
 * 4. Método próprio: aplicarRendimento():
 *    - Calcula: double rendimento = saldo * taxaRendimento;
 *    - Soma ao saldo: saldo += rendimento;
 *    - Exibe mensagem de juros creditados e novo saldo.
 * 
 * 5. Sobrescreva (@Override) exibirExtrato():
 *    - Imprime os dados básicos e a taxa de rendimento configurada.
 */
public class ContaPoupanca extends ContaBancaria {
    // TODO: Implemente os atributos, construtor e métodos aqui
    private double taxaRendimento;

    public ContaPoupanca(String titular, String numeroConta, double saldoInicial, double taxaRendimento) {
        super(titular, numeroConta, saldoInicial);
        this.taxaRendimento = taxaRendimento;
    }

    public void aplicarRendimento() {
        double rendimento = saldo * taxaRendimento;
        saldo += rendimento;
        System.out.println("Juros creditados: R$ " + rendimento);
        System.out.println("Novo saldo: R$ " + saldo);
    }

    @Override
    public void exibirExtrato() {
        super.exibirExtrato();
        System.out.println("Taxa de Rendimento: " + taxaRendimento);
    }

}
