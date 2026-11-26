package exercicio5;

import java.util.Scanner;

/*
 * EXERCÍCIO 5: Simulador de Rendimento de Investimentos Itaú (Laço FOR)
 * 
 * OBJETIVOS:
 * 1. Praticar a estrutura de repetição 'for' com contagem pré-definida.
 * 2. Praticar o uso de variáveis acumuladoras e operadores de atribuição (+=).
 * 3. Praticar formatação com printf (%02d para números com 2 dígitos, %.2f para dinheiro).
 * 4. Fechar o Scanner adequadamente.
 * 
 * ENUNCIADO:
 * Você vai criar um simulador de investimentos em CDB do Itaú para ajudar
 * os clientes a visualizarem a evolução do seu patrimônio mês a mês.
 * 
 * 1. Peça para o usuário informar via Scanner:
 *    - O valor inicial a ser investido (double, ex: 1000.00).
 *    - A quantidade de meses que o dinheiro ficará aplicado (int, ex: 6 ou 12).
 *    - A taxa de juros mensal em porcentagem (double, ex: 1.0 para 1% ao mês).
 * 
 * 2. Antes de iniciar o laço:
 *    - Converta a taxa percentual para decimal (taxaDecimal = taxaPercentual / 100).
 *    - Crie uma variável para acompanhar o saldo atual (iniciando com o valor investido).
 * 
 * 3. Use um laço FOR para percorrer cada mês (de 1 até a quantidade total de meses):
 *    - Em cada mês:
 *      a) Calcule o rendimento do mês: rendimento = saldoAtual * taxaDecimal;
 *      b) Some o rendimento ao saldoAtual (usando +=);
 *      c) Exiba a evolução do mês usando printf no seguinte formato:
 *         "Mês %02d | Rendimento: R$ %.2f | Saldo Acumulado: R$ %.2f%n"
 * 
 * 4. Ao final do laço (fora do for):
 *    - Calcule o lucro total obtido: (saldoAtual - valorInicial).
 *    - Exiba um resumo final:
 *      === RESUMO DO INVESTIMENTO ===
 *      Valor Inicial: R$ <valor inicial formatado>
 *      Lucro Total:   R$ <lucro formatado>
 *      Saldo Final:   R$ <saldo final formatado>
 * 
 * 5. Feche o scanner ao final.
 */
public class Exercicio5 {
    public static void main(String[] args) {
        // TODO: Escreva seu código aqui
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Bem-vindo ao simulador de investimento Itaú====");
        System.out.println("Insira o valor de investimento inicial");
        double valorInicial = scanner.nextDouble();
        System.out.println("Insira a quantidade de meses que o dinheiro ficará aplicado");
        int meses = scanner.nextInt();
        System.out.println("Insira a taxa de juros mensal em porcentagem");
        double taxaPercentual = scanner.nextDouble();
        double taxaDecimal = taxaPercentual / 100;
        double saldoAtual = valorInicial;

        System.out.println("\n--- EVOLUÇÃO MÊS A MÊS ---");
        for (int mes = 1; mes <= meses; mes++) {
            double rendimento = saldoAtual * taxaDecimal;
            saldoAtual += rendimento;
            System.out.printf("Mês %02d | Rendimento: R$ %.2f | Saldo Acumulado: R$ %.2f%n", mes, rendimento,
                    saldoAtual);
        }

        double lucro = saldoAtual - valorInicial;
        System.out.println("\n=== RESUMO DO INVESTIMENTO ===");
        System.out.printf("Valor Inicial: R$ %.2f%n", valorInicial);
        System.out.printf("Lucro Total:   R$ %.2f%n", lucro);
        System.out.printf("Saldo Final:   R$ %.2f%n", saldoAtual);

        scanner.close();
    }
}
