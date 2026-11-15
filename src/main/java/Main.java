import java.util.Scanner;

public class Main {

    private final static String WELCOME_MESSAGE = "Olá, bem vindo a primeira atividade do banco Itaú!";

    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.println("Digite sua idade: ");
        int idade = scanner.nextInt();
        System.out.println("Seu nome é " + nome + " e sua idade é " + idade + " anos");

        scanner.close();
    }
}
