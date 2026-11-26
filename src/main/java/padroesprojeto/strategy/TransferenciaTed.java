package padroesprojeto.strategy;

/**
 * Estratégia concreta: Transferência via TED (tarifa fixa de R$ 9,50).
 */
public class TransferenciaTed implements EstrategiaTransferencia {

    @Override
    public String getNome() {
        return "TED";
    }

    @Override
    public double calcularTaxa(double valor) {
        return 9.50;
    }
}
