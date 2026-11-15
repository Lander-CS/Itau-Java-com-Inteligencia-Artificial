package exercicio1;

import java.util.Scanner;

/*
 * EXERCÍCIO 1: Calculadora de Média do Aluno
 * 
 * OBJETIVOS:
 * 1. Praticar leitura com Scanner (texto e números decimais).
 * 2. Praticar operadores aritméticos (+ e /).
 * 3. Praticar saída formatada com printf (com 2 casas decimais e quebra de linha).
 * 4. Fechar o Scanner adequadamente.
 * 
 * REQUISITOS:
 * - Peça para o usuário digitar o nome do aluno (usando print ou println).
 * - Peça para digitar a nota da Prova 1 (double).
 * - Peça para digitar a nota da Prova 2 (double).
 * - Calcule a média simples: (nota1 + nota2) / 2.
 * - Usando printf, exiba o resultado no seguinte formato:
 *   Aluno: <nome> | Média Final: <media com 2 casas decimais>
 * - Não esqueça de fechar o scanner ao final!
 */
public class Exercicio1 {
    public static void main(String[] args) {
        // TODO: Escreva seu código aqui
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do aluno");
        String aluno = scanner.nextLine();
        System.out.println("Digite a nota da prova 1:");
        double prova1 = scanner.nextDouble();
        System.out.println("Digite a nota da prova 2:");
        double prova2 = scanner.nextDouble();

        var mediaFinal = (prova1 + prova2) / 2;

        System.out.printf("Aluno: " + aluno + " | Média final: " + "%.2f", mediaFinal);
        scanner.close();

    }
}
