package padroesprojeto.strategy;

/**
 * PADRÃO COMPORTAMENTAL: STRATEGY
 *
 * Define uma família de algoritmos de transferência intercambiáveis,
 * permitindo trocar a regra de cálculo de taxa dinamicamente em tempo de execução.
 */
public interface EstrategiaTransferencia {
    String getNome();
    double calcularTaxa(double valor);
}
