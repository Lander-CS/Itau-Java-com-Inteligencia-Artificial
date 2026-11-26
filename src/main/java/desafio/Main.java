package desafio;

import java.util.Scanner;

public class Main {

    static class Conta {
        private int saldo;

        public Conta(int saldoInicial) {
            this.saldo = saldoInicial;
        }

        public void depositar(int valor) {
            validarValor(valor);
            saldo += valor;
        }

        public void sacar(int valor) {
            validarValor(valor);

            if (valor > saldo) {
                throw new IllegalArgumentException("Erro: saldo insuficiente");
            }

            saldo -= valor;
        }

        public int getSaldo() {
            return saldo;
        }

        private void validarValor(int valor) {
            if (valor <= 0) {
                throw new IllegalArgumentException("Erro: valor invalido");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        String entrada = sb.toString().replace("\uFEFF", "").replaceAll("(?i)<br\\s*/?>", "\n").trim();
        if (entrada.isEmpty()) {
            return;
        }

        String[] partes = entrada.split("\\s+");
        String operacao = partes[0].trim();
        int saldoInicial = Integer.parseInt(partes[1].trim());
        int valorOperacao = Integer.parseInt(partes[2].trim());

        Conta conta = new Conta(saldoInicial);

        try {
            if ("DEPOSITO".equals(operacao)) {
                conta.depositar(valorOperacao);
                System.out.println("Saldo final: " + conta.getSaldo());
            } else if ("SAQUE".equals(operacao)) {
                conta.sacar(valorOperacao);
                System.out.println("Saldo final: " + conta.getSaldo());
            } else {
                System.out.println("Erro: operacao invalida");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
