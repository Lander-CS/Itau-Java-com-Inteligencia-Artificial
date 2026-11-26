package padroesprojeto.singleton;

/**
 * PADRÃO CRIACIONAL: SINGLETON (Lazy Holder - Thread-Safe)
 *
 * Garante que exista apenas UMA única instância de configuração global do banco
 * em toda a aplicação, com inicialização preguiçosa e segura para múltiplas threads.
 */
public class ConfiguracaoBancoSingleton {

    private final String nomeBanco;
    private final String codigoBanco;
    private double limiteAntifraude;

    private ConfiguracaoBancoSingleton() {
        this.nomeBanco = "Itaú Unibanco S.A.";
        this.codigoBanco = "341";
        this.limiteAntifraude = 5000.00;
    }

    private static class InstanceHolder {
        private static final ConfiguracaoBancoSingleton INSTANCIA = new ConfiguracaoBancoSingleton();
    }

    public static ConfiguracaoBancoSingleton getInstancia() {
        return InstanceHolder.INSTANCIA;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public double getLimiteAntifraude() {
        return limiteAntifraude;
    }

    public void setLimiteAntifraude(double limiteAntifraude) {
        this.limiteAntifraude = limiteAntifraude;
    }
}
