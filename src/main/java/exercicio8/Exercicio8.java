package exercicio8;

/**
 * EXERCÍCIO 8: Aplicação Executável
 * 
 * Demonstração Prática de:
 * 1. Polimorfismo baseado em Interfaces (Tributavel).
 * 2. Expressões Lambda com Interfaces Funcionais (ServicoNotificacao).
 * 3. Passagem de Lambdas como argumentos de métodos.
 */
public class Exercicio8 {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   PARTE 1: POLIMORFISMO COM INTERFACES (CONTRATO)");
        System.out.println("=================================================");

        // 1. Criamos dois produtos totalmente diferentes do Itaú:
        ContaInvestimento investimento = new ContaInvestimento("Lucas Silva", 5000.00);
        SeguroDeVida seguro = new SeguroDeVida("Lucas Silva", 100000.00);

        // 2. Usamos a calculadora genérica que só conhece 'Tributavel':
        CalculadoraDeTributos calculadora = new CalculadoraDeTributos();

        System.out.print("1. ");
        calculadora.registrar(investimento); // ContaInvestimento calcula 2% de 5000 = R$ 100,00

        System.out.print("2. ");
        calculadora.registrar(seguro);       // SeguroDeVida calcula taxa fixa = R$ 45,00

        System.out.printf("👉 Total de impostos recolhidos: R$ %.2f%n%n", calculadora.getTotalTributosAcumulados());


        System.out.println("=================================================");
        System.out.println("   PARTE 2: EXPRESSÕES LAMBDA (JAVA MODERNO)");
        System.out.println("=================================================");

        /*
         * O que é uma Lambda?
         * É uma forma ultra concisa de implementar a interface 'ServicoNotificacao'
         * sem precisar criar arquivos .java ou classes anônimas verbosas!
         * 
         * Sintaxe: (parametros) -> { o que fazer }
         */

        // Canal 1: Lambda para envio via SMS
        ServicoNotificacao canalSMS = (mensagem, destinatario) -> 
            System.out.printf("[📱 SMS para %s]: %s%n", destinatario, mensagem);

        // Canal 2: Lambda para envio via Push Notification no App Itaú
        ServicoNotificacao canalAppItau = (mensagem, destinatario) -> 
            System.out.printf("[🔔 NOTIFICAÇÃO APP ITAÚ - Cliente %s]: %s%n", destinatario, mensagem);

        // Canal 3: Lambda para envio via WhatsApp
        ServicoNotificacao canalWhatsApp = (mensagem, destinatario) -> 
            System.out.printf("[💬 WHATSAPP para %s]: Olá! %s (Itaú Unibanco)%n", destinatario, mensagem);

        // Testando os canais criados com Lambdas:
        canalSMS.enviar("Compra aprovada de R$ 89,90 no Cartão Virtual.", "11988887777");
        canalAppItau.enviar("Seu rendimento mensal do CDB foi creditado!", "Lucas Silva");
        canalWhatsApp.enviar("Sua fatura fechou. Código Pix para pagamento gerado.", "11988887777");

        System.out.println();
        System.out.println("=================================================");
        System.out.println("   PARTE 3: PASSANDO UMA LAMBDA COMO PARÂMETRO");
        System.out.println("=================================================");

        // Podemos passar a regra de notificação diretamente "on-the-fly" para um método:
        dispararAlertaSeguranca(
            "Tentativa de login suspeita em novo dispositivo!", 
            "lucas@email.com",
            (msg, dest) -> System.out.printf("[🚨 ALERTA CRÍTICO ENVIADO POR E-MAIL para %s]: %s%n", dest, msg)
        );
    }

    /**
     * Método auxiliar que recebe a Lambda como parâmetro.
     * Isso demonstra como frameworks reais como Spring Boot e Streams API funcionam!
     */
    public static void dispararAlertaSeguranca(String mensagem, String destinatario, ServicoNotificacao canal) {
        System.out.println("Processando protocolo de segurança do banco...");
        canal.enviar(mensagem, destinatario);
    }
}
