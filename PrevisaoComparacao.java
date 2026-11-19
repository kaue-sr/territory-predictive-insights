import java.util.ArrayList;

public class PrevisaoComparacao extends EstrategiaPrevisao {
    private double fatorTendencia;

    public PrevisaoComparacao(double fatorTendencia) {
        super("Comparação de Período", "Compara a leitura atual com a anterior e projeta tendência");
        this.fatorTendencia = fatorTendencia;
    }

    @Override
    public double prever(ArrayList<Double> leituras) {
        if (leituras == null || leituras.isEmpty()) {
            return 0.0;
        }
        if (leituras.size() == 1) {
            return leituras.get(0);
        }

        double atual = leituras.get(leituras.size() - 1);
        double anterior = leituras.get(leituras.size() - 2);
        double diferenca = atual - anterior;

        // Projeta o próximo valor somando a variação multiplicada pelo fator
        double previsao = atual + (diferenca * fatorTendencia);
        if (previsao < 0) {
            previsao = 0.0;
        }

        return Math.round(previsao * 100.0) / 100.0;
    }

    public double getFatorTendencia() {
        return fatorTendencia;
    }
}