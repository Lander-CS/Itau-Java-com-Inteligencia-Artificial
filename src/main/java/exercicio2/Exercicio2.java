package exercicio2;

import java.util.Scanner;

/*
 * EXERCÍCIO 2: Sistema de Aprovação de Empréstimo
 * 
 * OBJETIVOS:
 * 1. Praticar operadores relacionais (>=, <=, etc.) e lógicos (&&, ||, !).
 * 2. Praticar o operador ternário (? :).
 * 3. Praticar leitura de booleano e números com Scanner.
 * 
 * REQUISITOS:
 * - Peça a idade do solicitante (int).
 * - Peça a renda mensal do solicitante (double).
 * - Pergunte se ele possui restrição no nome / negativado (boolean: true ou false).
 * 
 * REGRA DO BANCO:
 * Para ser APROVADO, o cliente precisa:
 * - Ter idade maior ou igual a 18 E menor ou igual a 65 (idade >= 18 && idade <= 65)
 * - Ter renda mensal maior ou igual a 2500.00 (renda >= 2500.00)
 * - NÃO ter restrição no nome (!temRestricao)
 * 
 * - Guarde o resultado da condição em uma variável booleana chamada 'aprovado'.
 * - Use o OPERADOR TERNÁRIO para definir uma String com a mensagem:
 *   "Empréstimo APROVADO!" caso true, ou "Empréstimo REPROVADO!" caso false.
 * - Imprima a mensagem na tela.
 * - Feche o scanner.
 */
public class Exercicio2 {
    public static void main(String[] args) {
        // TODO: Escreva seu código aqui
        Scanner scanner = new Scanner(System.in);
        System.out.println("Olá, seja muito bem-vindo ao sistema do banco Itaú!");
        System.out.println("Por favor, nos informe seu nome: ");
        var nome = scanner.nextLine();
        System.out.println("Olá, " + nome + "! Agora, nos informe a sua idade: ");
        int idade = scanner.nextInt();
        System.out.println("Agora, nos informe sua renda mensal: ");
        double renda = scanner.nextDouble();
        System.out.println("Por fim, nos informe se você possui alguma restrição em seu nome! true/false");
        boolean temRestricao = scanner.nextBoolean();
        boolean aprovado = renda >= 2500.00 && !temRestricao && idade >= 18 && idade <= 65;

        String mensagem = aprovado ? " APROVADO!" : " REPROVADO!";
        System.out.println("Olá," + nome + " O status do seu empréstimo é:" + mensagem);

        scanner.close();
    }
}
