public abstract class placaCircuito {
    private final int id;
    private final String nome;
    private String status;
    private final double quantidadeMateriaPrimaPorUnidade;
    private double qualidade;
    private double probabilidadeFalhaAcumulada;
    private int totalProdutosFabricados = 0;

    public placaCircuito(int id,String nome, double quantidadeMateriaPrimaPorUnidade, double qualidade) {
        this.id = id;
        this.nome = nome;
        this.status = "EM_PRODUCAO";
        this.quantidadeMateriaPrimaPorUnidade = quantidadeMateriaPrimaPorUnidade;
        this.qualidade = qualidade;
        this.probabilidadeFalhaAcumulada = 0.0;
    }
    public abstract void processar();

    public abstract int calcularTempoProducao();

    public abstract String getTipo();

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getQuantidadeMateriaPrimaPorUnidade() {
        return quantidadeMateriaPrimaPorUnidade;
    }

    public double getQualidade() {
        return qualidade;
    }

    public double getProbabilidadeFalhaAcumulada() {
        return probabilidadeFalhaAcumulada;
    }

    public void aumentarProbabilidadeFalha(double incremento) {
        this.probabilidadeFalhaAcumulada = Math.min(1.0, this.probabilidadeFalhaAcumulada+incremento);
    }
    public int getTotalProdutosFabricados() {
        return totalProdutosFabricados;
    }

    @Override
    public String toString() {
        return String.format("#%d %s [%s] qualidade=%.1f falhaAcumulada=%.2f status=%s",
                id, nome, getTipo(), qualidade, probabilidadeFalhaAcumulada, status);
    }

}
