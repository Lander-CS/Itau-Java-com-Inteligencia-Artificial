package exercicio7;

/*
 * EXERCÍCIO 7 - PARTE 4: A Classe Executável (Testando Polimorfismo)
 * 
 * OBJETIVOS:
 * 1. Instanciar uma ContaCorrente e uma ContaPoupanca.
 * 2. Guardar ambas em variáveis do tipo mais genérico 'ContaBancaria' (Polimorfismo de Referência!).
 * 3. Realizar saques de mesmo valor em ambas e notar a diferença de comportamento:
 *    - A ContaCorrente cobra taxa de R$ 2.50 e pode usar cheque especial.
 *    - A ContaPoupanca não cobra taxa e só saca se tiver saldo positivo.
 * 4. Chamar aplicarRendimento() na Poupança e exibir os extratos finais.
 */
public class Exercicio7 {
    public static void main(String[] args) {
        // TODO: Escreva seu código de teste aqui
        ContaCorrente conta1 = new ContaCorrente("Ana", "001", 1000, 500);
        ContaPoupanca conta2 = new ContaPoupanca("Bruno", "002", 1000, 0.01);

        conta1.exibirExtrato();
        conta2.exibirExtrato();

        conta1.sacar(500);
        conta2.sacar(500);

        conta1.exibirExtrato();
        conta2.exibirExtrato();

        conta2.aplicarRendimento();
        conta2.exibirExtrato();

    }
}
