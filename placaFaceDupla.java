public class placaFaceDupla extends placaCircuito {

    private static final double QUALIDADE = 0.7;
    private static final double COBRE_POR_UNIDADE = 70.0;
    private static final int id = 020;

    public placaFaceDupla(String nome) {
        super(id ,nome, COBRE_POR_UNIDADE, QUALIDADE);
    }

    @Override
    public void processar() {
        System.out.println("Gravando trilhas nas duas faces de " + getNome() + "...");
    }

    @Override
    public int calcularTempoProducao() {
        return 25;
    }

    @Override
    public String getTipo() {
        return "Dupla Face";
    }
}
