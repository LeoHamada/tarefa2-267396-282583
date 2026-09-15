public class placaFaceUnica extends placaCircuito {
    private static final double QUALIDADE = 0.5;
    private static final double COBRE_POR_UNIDADE = 35.0;

    public placaFaceUnica(String nome){
        super(nome, COBRE_POR_UNIDADE, QUALIDADE);
    }

    @Override
    public void processar() {
        System.out.println("Gravando trilhas simples em " + getNome() + "...");
    }

    @Override
    public int calcularTempoProducao() {
        return 12;
    }

    @Override
    public String getTipo() {
        return "Face Única";
    }
}