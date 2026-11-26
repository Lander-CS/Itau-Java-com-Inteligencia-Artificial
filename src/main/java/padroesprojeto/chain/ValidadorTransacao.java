package padroesprojeto.chain;

import padroesprojeto.model.ContaDigital;

/**
 * PADRÃO COMPORTAMENTAL: CHAIN OF RESPONSIBILITY
 *
 * Encadeia múltiplos validadores independentes em sequência.
 * Cada elo valida uma regra específica e, se aprovado, passa a requisição para o próximo elo.
 */
public abstract class ValidadorTransacao {

    protected ValidadorTransacao proximo;

    public ValidadorTransacao encadear(ValidadorTransacao proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public abstract void validar(ContaDigital conta, double valorTotal);

    protected void chamarProximo(ContaDigital conta, double valorTotal) {
        if (proximo != null) {
            proximo.validar(conta, valorTotal);
        }
    }
}
