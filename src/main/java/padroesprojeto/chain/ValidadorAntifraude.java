package padroesprojeto.chain;

import padroesprojeto.model.ContaDigital;
import padroesprojeto.singleton.ConfiguracaoBancoSingleton;

/**
 * Elo 3 da Cadeia: Verifica o limite antifraude definido no Singleton de configuração do banco.
 */
public class ValidadorAntifraude extends ValidadorTransacao {

    @Override
    public void validar(ContaDigital conta, double valorTotal) {
        double limite = ConfiguracaoBancoSingleton.getInstancia().getLimiteAntifraude();
        if (valorTotal > limite) {
            throw new IllegalArgumentException(
                String.format("Bloqueio Antifraude: Valor R$ %.2f excede o limite de segurança de R$ %.2f.",
                    valorTotal, limite)
            );
        }
        chamarProximo(conta, valorTotal);
    }
}
