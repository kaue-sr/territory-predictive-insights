public class DadosTerritorio {
    private String nomeRegiao;
    private int populacao;
    private double limiarChuva;
    private double limiarNivelRio;
    private double limiarUmidade;
    private String codigoAcesso;

    public DadosTerritorio(String nomeRegiao, int populacao, double limiarChuva, double limiarNivelRio, double limiarUmidade, String codigoAcesso) {
        this.nomeRegiao = nomeRegiao;
        this.populacao = populacao;
        this.limiarChuva = limiarChuva;
        this.limiarNivelRio = limiarNivelRio;
        this.limiarUmidade = limiarUmidade;
        this.codigoAcesso = codigoAcesso;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        if (nomeRegiao == null || nomeRegiao.trim().isEmpty()) {
            System.out.println("[ERRO] Nome da região não pode ser vazio. Valor anterior mantido.");
            return;
        }
        this.nomeRegiao = nomeRegiao;
        System.out.println("[OK] Nome da região atualizado para: " + nomeRegiao);
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        if (populacao <= 0) {
            System.out.println("[ERRO] A população deve ser maior que zero. Valor anterior mantido (" + this.populacao + ").");
            return;
        }
        this.populacao = populacao;
        System.out.println("[OK] População atualizada para: " + populacao + " habitantes.");
    }

    public double getLimiarChuva() {
        return limiarChuva;
    }

    public void setLimiarChuva(double novoLimiar, String codigoInformado) {
        if (!validarCodigo(codigoInformado)) {
            return;
        }
        if (novoLimiar < 10.0 || novoLimiar > 200.0) {
            System.out.println("[ERRO] Limiar de chuva inválido (aceito entre 10 mm e 200 mm). Valor anterior mantido (" + this.limiarChuva + " mm).");
            return;
        }
        this.limiarChuva = novoLimiar;
        System.out.println("[OK] Limiar de chuva atualizado para: " + novoLimiar + " mm.");
    }

    public double getLimiarNivelRio() {
        return limiarNivelRio;
    }

    public void setLimiarNivelRio(double novoLimiar, String codigoInformado) {
        if (!validarCodigo(codigoInformado)) {
            return;
        }
        if (novoLimiar < 1.0 || novoLimiar > 15.0) {
            System.out.println("[ERRO] Limiar do nível do rio inválido (aceito entre 1.0 m e 15.0 m). Valor anterior mantido (" + this.limiarNivelRio + " m).");
            return;
        }
        this.limiarNivelRio = novoLimiar;
        System.out.println("[OK] Limiar do nível do rio atualizado para: " + novoLimiar + " m.");
    }

    public double getLimiarUmidade() {
        return limiarUmidade;
    }

    public void setLimiarUmidade(double novoLimiar, String codigoInformado) {
        if (!validarCodigo(codigoInformado)) {
            return;
        }
        if (novoLimiar < 30.0 || novoLimiar > 100.0) {
            System.out.println("[ERRO] Limiar de umidade inválido (aceito entre 30% e 100%). Valor anterior mantido (" + this.limiarUmidade + "%).");
            return;
        }
        this.limiarUmidade = novoLimiar;
        System.out.println("[OK] Limiar de umidade atualizado para: " + novoLimiar + "%.");
    }

    private boolean validarCodigo(String codigoInformado) {
        if (codigoInformado == null || !codigoInformado.equals(this.codigoAcesso)) {
            System.out.println("[ACESSO NEGADO] Código de acesso incorreto! Alteração rejeitada.");
            return false;
        }
        return true;
    }
}