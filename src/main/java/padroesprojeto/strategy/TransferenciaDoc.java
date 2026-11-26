package padroesprojeto.strategy;

/**
 * Estratégia concreta: Transferência via DOC (1% sobre o valor, mínimo de R$ 5,00).
 */
public class TransferenciaDoc implements EstrategiaTransferencia {

    @Override
    public String getNome() {
        return "DOC";
    }

    @Override
    public double calcularTaxa(double valor) {
        return Math.max(5.00, valor * 0.01);
    }
}
