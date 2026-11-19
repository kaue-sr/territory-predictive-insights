import java.util.ArrayList;

public abstract class EstrategiaPrevisao {
    private String nomeEstrategia;
    private String descricao;

    public EstrategiaPrevisao(String nomeEstrategia, String descricao) {
        this.nomeEstrategia = nomeEstrategia;
        this.descricao = descricao;
    }

    // Método abstrato que cada tipo de previsão vai implementar 
    public abstract double prever(ArrayList<Double> leituras);

    // Método concreto reutilizado pelas subclasses
    public String gerarResumo(double valorPrevisto, String unidade) {
        return "[" + nomeEstrategia + "] Previsão: " + valorPrevisto + " " + unidade + " (" + descricao + ")";
    }

    public String getNomeEstrategia() {
        return nomeEstrategia;
    }

    public String getDescricao() {
        return descricao;
    }
}