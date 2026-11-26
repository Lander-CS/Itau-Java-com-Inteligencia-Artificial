package padroesprojeto.strategy;

/**
 * Estratégia concreta: Transferência via PIX (isenta de tarifas).
 */
public class TransferenciaPix implements EstrategiaTransferencia {

    @Override
    public String getNome() {
        return "PIX";
    }

    @Override
    public double calcularTaxa(double valor) {
        return 0.00;
    }
}
