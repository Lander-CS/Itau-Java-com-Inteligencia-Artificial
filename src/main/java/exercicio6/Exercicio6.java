package exercicio6;

import java.util.Scanner;

/*
 * EXERCÍCIO 6 - PARTE 2: A Classe Executável (Menu Interativo do Banco Itaú)
 * 
 * OBJETIVOS:
 * 1. Conectar a interface do usuário (Menu no terminal) com o Objeto (ContaCorrente).
 * 2. Praticar a chamada de métodos encapsulados através da instância (landersContaCorrente).
 * 3. Utilizar o laço do-while e switch moderno para navegação do menu.
 */
public class Exercicio6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Instanciando o objeto da Conta Corrente
        ContaCorrente landersContaCorrente = new ContaCorrente("Landerson", "0001", "12345-6", 500.00, 300.00);
        
        int opcao;

        System.out.printf("Olá, %s! Bem-vindo ao sistema de autoatendimento do Itaú.%n", landersContaCorrente.getTitular());

        do {
            System.out.println("\n========================================");
            System.out.println("        BANCO ITAÚ - CONTA CORRENTE     ");
            System.out.println("========================================");
            System.out.println(" 1 - Consultar Extrato Completo");
            System.out.println(" 2 - Consultar Saldo Rápido");
            System.out.println(" 3 - Realizar Depósito");
            System.out.println(" 4 - Realizar Saque");
            System.out.println(" 5 - Sair do Sistema");
            System.out.println("========================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.println("\n--- EXTRATO BANCÁRIO ---");
                    landersContaCorrente.exibirExtrato();
                    System.out.println();
                }

                case 2 -> {
                    System.out.printf("\nSaldo atual em conta: R$ %.2f%n", landersContaCorrente.getSaldo());
                    System.out.printf("Limite de Cheque Especial disponível: R$ %.2f%n", landersContaCorrente.getLimiteChequeEspecial());
                }

                case 3 -> {
                    System.out.print("\nDigite o valor que deseja depositar: R$ ");
                    double valorDeposito = scanner.nextDouble();
                    landersContaCorrente.depositar(valorDeposito);
                    System.out.println();
                }

                case 4 -> {
                    System.out.print("\nDigite o valor que deseja sacar: R$ ");
                    double valorSaque = scanner.nextDouble();
                    landersContaCorrente.sacar(valorSaque);
                    System.out.println();
                }

                case 5 -> System.out.println("\nObrigado por utilizar os serviços do Itaú! Tenha um ótimo dia.");

                default -> System.out.println("\n⚠️ Opção inválida! Por favor, escolha um número de 1 a 5.");
            }

        } while (opcao != 5);

        scanner.close();
    }
}
