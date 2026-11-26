package exercicio8;

/**
 * POLIMORFISMO COM INTERFACES
 * 
 * A calculadora não precisa conhecer nem 'ContaInvestimento' nem 'SeguroDeVida'.
 * Ela aceita qualquer coisa que assine a interface 'Tributavel'!
 * Se amanhã o Itaú criar 'Consorcio' ou 'PrevidenciaPrivada' que implementem Tributavel,
 * esta calculadora funcionará perfeitamente sem precisar mudar nenhuma linha de código!
 */
public class CalculadoraDeTributos {
    private double totalTributosAcumulados = 0.0;

    public void registrar(Tributavel item) {
        double imposto = item.calcularTributo();
        this.totalTributosAcumulados += imposto;
        System.out.printf("Tributo calculado para o item: R$ %.2f%n", imposto);
    }

    public double getTotalTributosAcumulados() {
        return totalTributosAcumulados;
    }
}
