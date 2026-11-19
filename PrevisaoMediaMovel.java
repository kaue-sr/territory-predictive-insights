import java.util.ArrayList;

public class PrevisaoMediaMovel extends EstrategiaPrevisao {
    private int tamanhoJanela;

    public PrevisaoMediaMovel(int tamanhoJanela) {
        super("Média Móvel", "Média das últimas " + tamanhoJanela + " leituras");
        this.tamanhoJanela = tamanhoJanela;
    }

    @Override
    public double prever(ArrayList<Double> leituras) {
        if (leituras == null || leituras.isEmpty()) {
            return 0.0;
        }

        // Define quantas leituras pegar (se tiver menos que a janela, pega todas)
        int quantidade = Math.min(tamanhoJanela, leituras.size());
        double soma = 0.0;

        for (int i = leituras.size() - quantidade; i < leituras.size(); i++) {
            soma += leituras.get(i);
        }

        double media = soma / quantidade;
        return Math.round(media * 100.0) / 100.0;
    }

    public int getTamanhoJanela() {
        return tamanhoJanela;
    }
}