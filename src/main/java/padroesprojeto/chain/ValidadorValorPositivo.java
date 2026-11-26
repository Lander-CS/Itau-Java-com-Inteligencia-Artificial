package padroesprojeto.chain;

import padroesprojeto.model.ContaDigital;

/**
 * Elo 1 da Cadeia: Garante que o valor da operação seja positivo.
 */
public class ValidadorValorPositivo extends ValidadorTransacao {

    @Override
    public void validar(ContaDigital conta, double valorTotal) {
        if (valorTotal <= 0) {
            throw new IllegalArgumentException("Erro de validação: O valor da transferência deve ser maior que zero.");
        }
        chamarProximo(conta, valorTotal);
    }
}
