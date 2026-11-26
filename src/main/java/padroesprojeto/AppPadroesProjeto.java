package padroesprojeto;

import padroesprojeto.facade.ItauBancoFacade;
import padroesprojeto.model.ContaDigital;
import padroesprojeto.singleton.ConfiguracaoBancoSingleton;
import padroesprojeto.strategy.EstrategiaTransferencia;
import padroesprojeto.strategy.TransferenciaDoc;
import padroesprojeto.strategy.TransferenciaPix;
import padroesprojeto.strategy.TransferenciaTed;

/**
 * DESAFIO DE PROJETO DIO: Padrões de Projeto (GoF) na Prática com Java
 *
 * Demonstra a integração de 4 Design Patterns clássicos do GoF em um domínio bancário Itaú:
 * 1. Singleton (Criacional): ConfiguracaoBancoSingleton e LogAuditoriaSingleton
 * 2. Strategy (Comportamental): TransferenciaPix, TransferenciaTed, TransferenciaDoc
 * 3. Chain of Responsibility (Comportamental): ValidadorValorPositivo -> ValidadorSaldoDisponivel -> ValidadorAntifraude
 * 4. Facade (Estrutural): ItauBancoFacade unificando todo o fluxo em uma chamada simples
 */
public class AppPadroesProjeto {

    public static void main(String[] args) {
        System.out.println("========================================================");
        System.out.println("   BOOTCAMP ITAÚ + DIO | DESIGN PATTERNS (GoF) EM JAVA  ");
        System.out.println("========================================================");

        // 1. Comprovando o padrão SINGLETON (mesma instância na memória)
        ConfiguracaoBancoSingleton configA = ConfiguracaoBancoSingleton.getInstancia();
        ConfiguracaoBancoSingleton configB = ConfiguracaoBancoSingleton.getInstancia();
        System.out.println("\n[Teste Singleton] Mesma instância em memória? " + (configA == configB));
        System.out.printf("[Teste Singleton] Banco: %s | Limite Antifraude: R$ %.2f%n",
            configA.getNomeBanco(), configA.getLimiteAntifraude());

        // 2. Criando contas digitais
        ContaDigital contaLander = new ContaDigital("Landerson", "1001-1", 10000.00);
        ContaDigital contaMaria = new ContaDigital("Maria", "2002-2", 1000.00);

        // 3. Instanciando as estratégias (STRATEGY)
        EstrategiaTransferencia pix = new TransferenciaPix();
        EstrategiaTransferencia ted = new TransferenciaTed();
        EstrategiaTransferencia doc = new TransferenciaDoc();

        // 4. Usando a Fachada (FACADE) que orquestra Strategy + Chain of Responsibility + Singleton
        ItauBancoFacade itau = new ItauBancoFacade();

        // Teste 1: PIX válido (tarifa zero)
        itau.realizarTransferencia(contaLander, contaMaria, 200.00, pix);

        // Teste 2: TED válido (tarifa fixa R$ 9,50)
        itau.realizarTransferencia(contaLander, contaMaria, 500.00, ted);

        // Teste 3: DOC válido (1% de taxa = R$ 10,00)
        itau.realizarTransferencia(contaLander, contaMaria, 1000.00, doc);

        // Teste 4: Erro capturado pelo Elo 1 da Chain (Valor <= 0)
        itau.realizarTransferencia(contaLander, contaMaria, -50.00, pix);

        // Teste 5: Erro capturado pelo Elo 3 da Chain (Antifraude > R$ 5.000,00)
        itau.realizarTransferencia(contaLander, contaMaria, 5500.00, pix);

        // Teste 6: Erro capturado pelo Elo 2 da Chain (Saldo insuficiente)
        itau.realizarTransferencia(contaMaria, contaLander, 4000.00, ted);

        // 5. Exibindo o histórico completo registrado pelo Singleton de Auditoria
        itau.exibirRelatorioAuditoria();
    }
}
