package exercicio3;

import java.util.Scanner;

/*
 * EXERCÍCIO 3: Verificador Par/Ímpar e Atualizador de Pontuação
 * 
 * OBJETIVOS:
 * 1. Praticar operador de módulo % (resto da divisão).
 * 2. Praticar operadores de atribuição composta (+=, -=) e incremento (++).
 * 3. Praticar operadores relacionais e lógicos.
 * 
 * REQUISITOS:
 * - Comece com uma variável de pontuação: int pontos = 100;
 * - Peça para o usuário digitar um número inteiro qualquer.
 * - Verifique se o número digitado é PAR ou ÍMPAR usando o operador %:
 *   (Dica: se numero % 2 == 0 é par, senão é ímpar).
 * - Exiba se o número é "Par" ou "Ímpar".
 * - REGRAS DE PONTUAÇÃO:
 *   - Se o número for PAR: adicione 20 pontos à pontuação usando o operador de atribuição +=
 *   - Se o número for ÍMPAR: subtraia 10 pontos usando o operador -=
 *   - Se o número for maior que 100: incremente 1 ponto bônus usando ++
 * - Exiba a pontuação final usando printf: "Pontuação final: %d pontos%n"
 * - Feche o scanner.
 */
public class Exercicio3 {
    public static void main(String[] args) {
        // TODO: Escreva seu código aqui
        Scanner scanner = new Scanner(System.in);
        int pontos = 100;
        System.out.println("Digite um número INTEIRO qualquer: ");
        int numero = scanner.nextInt();
        boolean éPar = numero % 2 == 0;

        if (éPar) {
            System.out.println("O numero digitado é par");
            pontos += 20;
        }

        else {
            System.out.println("O numero digitado é ímpar");
            pontos -= 10;
        }

        if (numero > 100) {
            System.out.println("Você ganhou 1 ponto bônus!");
            pontos++;
        }
        System.out.printf("Pontuação final: %d pontos%n", pontos);
        scanner.close();

    }
}
