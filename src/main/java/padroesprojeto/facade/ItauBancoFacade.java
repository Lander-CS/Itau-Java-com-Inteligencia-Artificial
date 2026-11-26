package padroesprojeto.facade;

import padroesprojeto.chain.ValidadorAntifraude;
import padroesprojeto.chain.ValidadorSaldoDisponivel;
import padroesprojeto.chain.ValidadorTransacao;
import padroesprojeto.chain.ValidadorValorPositivo;
import padroesprojeto.model.ContaDigital;
import padroesprojeto.singleton.ConfiguracaoBancoSingleton;
import padroesprojeto.singleton.LogAuditoriaSingleton;
import padroesprojeto.strategy.EstrategiaTransferencia;

/**
 * PADRÃO ESTRUTURAL: FACADE (Fachada)
 *
 * Oferece uma interface simplificada para o cliente realizar transferências bancárias,
 * escondendo toda a complexidade interna de:
 * - Configurações globais (Singleton)
 * - Cálculo dinâmico de tarifas (Strategy)
 * - Cadeia de validações de segurança e saldo (Chain of Responsibility)
 * - Registro centralizado de auditoria (Singleton)
 */
public class ItauBancoFacade {

    private final ValidadorTransacao cadeiaValidacao;
    private final LogAuditoriaSingleton auditoria;
    private final ConfiguracaoBancoSingleton config;

    public ItauBancoFacade() {
        this.config = ConfiguracaoBancoSingleton.getInstancia();
        this.auditoria = LogAuditoriaSingleton.getInstancia();

        // Montagem da cadeia de responsabilidade (Chain of Responsibility)
        ValidadorTransacao v1 = new ValidadorValorPositivo();
        ValidadorTransacao v2 = new ValidadorSaldoDisponivel();
        ValidadorTransacao v3 = new ValidadorAntifraude();
        v1.encadear(v2).encadear(v3);
        this.cadeiaValidacao = v1;
    }

    public void realizarTransferencia(ContaDigital origem, ContaDigital destino, double valor, EstrategiaTransferencia estrategia) {
        System.out.printf("%n[%s - Cód. %s] Iniciando transferência via %s de R$ %.2f (%s -> %s)...%n",
            config.getNomeBanco(), config.getCodigoBanco(), estrategia.getNome(),
            valor, origem.getTitular(), destino.getTitular());

        double taxa = estrategia.calcularTaxa(valor);
        double valorTotal = valor + taxa;

        try {
            // 1. Executa toda a cadeia de validações
            cadeiaValidacao.validar(origem, valorTotal);

            // 2. Efetiva a movimentação entre as contas
            origem.debitar(valorTotal);
            destino.creditar(valor);

            String msgSucesso = String.format(
                "SUCESSO (%s): %s transferiu R$ %.2f para %s | Tarifa: R$ %.2f | Novo saldo: R$ %.2f",
                estrategia.getNome(), origem.getTitular(), valor, destino.getTitular(), taxa, origem.getSaldo()
            );

            System.out.println(" " + msgSucesso);
            auditoria.registrar(msgSucesso);

        } catch (IllegalArgumentException e) {
            String msgFalha = String.format(
                "FALHA (%s): Tentativa de R$ %.2f por %s recusada -> %s",
                estrategia.getNome(), valor, origem.getTitular(), e.getMessage()
            );

            System.out.println("❌ " + e.getMessage());
            auditoria.registrar(msgFalha);
        }
    }

    public void exibirRelatorioAuditoria() {
        System.out.println("\n========================================================");
        System.out.println("   RELATÓRIO DE AUDITORIA (Singleton LogAuditoria)      ");
        System.out.println("========================================================");
        for (String registro : auditoria.getHistorico()) {
            System.out.println(registro);
        }
        System.out.println("========================================================");
    }
}
