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

        if (verificFalha()) {
            System.out.println(" [ERRO] Falha na inspeção " + placa.getNome());
        }
    }
    
}
