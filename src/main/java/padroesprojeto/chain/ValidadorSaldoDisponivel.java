package padroesprojeto.chain;

import padroesprojeto.model.ContaDigital;

/**
 * Elo 2 da Cadeia: Garante que a conta possua saldo suficiente (incluindo tarifa).
 */
public class ValidadorSaldoDisponivel extends ValidadorTransacao {

    @Override
    public void validar(ContaDigital conta, double valorTotal) {
        if (valorTotal > conta.getSaldo()) {
            throw new IllegalArgumentException(
                String.format("Erro de validação: Saldo insuficiente! Necessário: R$ %.2f | Disponível: R$ %.2f",
                    valorTotal, conta.getSaldo())
            );
        }
        chamarProximo(conta, valorTotal);
    }
}
