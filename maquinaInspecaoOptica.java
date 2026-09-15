public class maquinaInspecaoOptica extends maquinaGenerica{

    private static final int CAPACIDADE_MAXIMA = 40;
    private static final double PROBABILIDADE_FALHA = 0.05;
    private static final double CUSTO_OPERACAO = 20.0;

    public maquinaInspecaoOptica(String nome, boolean ligada) {
        super(nome, ligada, CAPACIDADE_MAXIMA, PROBABILIDADE_FALHA, CUSTO_OPERACAO);
    }

    @Override
    public String getTipo() {
        return "Inspeção Óptica Automatica";
    }

    @Override
    public void processar(placaCircuito placa) {
        System.out.println(getnome() + " Inspecionando " + placa.getNome() + "...");

        double chanceRejeicao = PROBABILIDADE_FALHA
                + (placa.getQualidade() * 0.3)
                + (placa.getProbabilidadeFalhaAcumulada() * 0.5);
        chanceRejeicao = Math.min(1.0, chanceRejeicao);

        if (sortear(chanceRejeicao)) {
            placa.setStatus("REJEITADA");
            System.out.println(" [ERRO] " + placa.getNome() + " reprovada na inspeção (chance de rejeição=" 
                    + String.format("%.2f", chanceRejeicao) + ")");
        } else {
            placa.setStatus("APROVADA");
            System.out.println(" " + placa.getNome() + " aprovada na inspeção.");
        }
    }
    
}