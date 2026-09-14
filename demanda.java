public class demanda {

    private final String tipoProduto;
    private int quantidadeProdutos;
    private boolean atendida;

    public demanda(String tipoProduto, int quantidadeProdutos) {
        this.tipoProduto = tipoProduto;
        this.quantidadeProdutos = quantidadeProdutos;
        this.atendida = quantidadeProdutos <= 0;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public int getQuantidadeProdutos() {
        return quantidadeProdutos;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void atualizarQuantidade(int novaQuantidade) {
        this.quantidadeProdutos = novaQuantidade;
        this.atendida = novaQuantidade <= 0;
    }

    public double calcularMateriaPrimaNecessaria(double cobrePorUnidade) {
        return quantidadeProdutos * cobrePorUnidade;
    }

    public void atender(int quantidadeEntregue) {
        this.quantidadeProdutos = Math.max(0, this.quantidadeProdutos - quantidadeEntregue);
        if (this.quantidadeProdutos == 0) {
            this.atendida = true;
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %d unidade(s) pendente(s) [%s]",
                tipoProduto, quantidadeProdutos, atendida ? "ATENDIDA" : "PENDENTE");
    }
}
