package exercicio4;

import java.util.Scanner;

/*
 * EXERCÍCIO 4: Caixa Eletrônico Itaú (Fixação de Switch Case)
 * 
 * OBJETIVOS:
 * 1. Praticar a estrutura condicional switch/case.
 * 2. Praticar o uso de 'default' para opções inválidas.
 * 3. Praticar a leitura de opções com Scanner.
 * 4. Integrar com operadores de atribuição (+=, -=) e printf (R$ %.2f).
 * 
 * ENUNCIADO:
 * Você foi contratado para criar a lógica de um terminal de autoatendimento do Itaú!
 * 
 * O cliente começa com um saldo inicial pré-definido:
 *     double saldo = 1000.00;
 * 
 * 1. Exiba um menu de opções no console:
 *    === CAIXA ELETRÔNICO ITAÚ ===
 *    1 - Consultar Saldo
 *    2 - Fazer Depósito
 *    3 - Fazer Saque
 *    4 - Sair do Sistema
 *    Escolha uma opção:
 * 
 * 2. Leia a opção digitada pelo usuário (int).
 * 
 * 3. Use a estrutura SWITCH CASE para tratar a opção:
 *    - Caso 1: Exiba o saldo atual formatado com printf (ex: "Seu saldo atual é: R$ %.2f%n", saldo).
 *    - Caso 2: Peça o valor a ser depositado (double).
 *              Some ao saldo (usando +=) e exiba o novo saldo com printf.
 *    - Caso 3: Peça o valor a ser sacado (double).
 *              Verifique se o valor do saque é menor ou igual ao saldo (valorSaque <= saldo):
 *                - Se sim: subtraia do saldo (-=) e exiba o novo saldo.
 *                - Se não: exiba "Saldo insuficiente para realizar este saque!".
 *    - Caso 4: Exiba "Obrigado por utilizar os serviços do Itaú! Até logo."
 *    - Default: Exiba "Opção inválida! Por favor, escolha um número de 1 a 4."
 * 
 * 4. Lembre-se de usar 'break' nos casos (se usar o switch tradicional) e fechar o scanner ao final!
 */
public class Exercicio4 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double saldo = 1000.00;
        int opcao;
        do {
            System.out.println(" === CAIXA ELETRÔNICO ITAÚ === ");
            System.out.println(" 1 - Consultar Saldo.");
            System.out.println(" 2 - Fazer Depósito.");
            System.out.println(" 3 - Fazer Saque.");
            System.out.println(" 4 - Sair do Sistema");
            System.out.print("Escolha uma opção:");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1 -> System.out.printf("Seu saldo atual é: R$ %.2f%n", saldo);
                case 2 -> {
                    System.out.println("Digite o valor do depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    saldo += valorDeposito;
                    System.out.printf("Novo saldo: R$ %.2f%n", saldo);
                }
                case 3 -> {
                    System.out.println("Digite o valor do saque: ");
                    double valorSaque = scanner.nextDouble();
                    if (saldo >= valorSaque) {
                        saldo -= valorSaque;
                        System.out.printf("Novo saldo: R$ %.2f%n", saldo);
                    } else {
                        System.out.println("Saldo insuficiente para realizar este saque!");
                    }
                }
                case 4 -> System.out.println("Obrigado por utilizar os serviços do Itaú! Até logo.");
                default -> System.out.println("Opção inválida! Escolha de 1 a 4");
            }
        } while (opcao != 4);
        scanner.close();
    }

}
