public class placaMultilayerPro extends placaCircuito {
    private static final double QUALIDADE = 0.9;
    private static final double COBRE_POR_UNIDADE = 120.0;

    public placaMultilayerPro(String nome) {
        super(nome, COBRE_POR_UNIDADE, QUALIDADE);
    }

    @Override
    public void processar() {
        System.out.println("Gravando " + calcularTempoProducao() + "s de trilhas multicamada em " + getNome() + "...");
    }

    @Override
    public int calcularTempoProducao() {
        return 45;
    }

    @Override
    public String getTipo() {
        return "Multilayer Pro";
    }
}
