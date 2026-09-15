public class maquinaLaminadora extends maquinaGenerica {

    private static final int CAPACIDADE_MAXIMA = 30;
    private static final double PROBABILIDADE_FALHA = 0.2;
    private static final double CUSTO_OPERACAO = 10.0;
    private static final double AUMENTO_FALHA = 0.2; 

    public maquinaLaminadora(String nome, boolean ligada) {
        super(nome, ligada, CAPACIDADE_MAXIMA, PROBABILIDADE_FALHA, CUSTO_OPERACAO);
    }

    @Override
    public String getTipo() {
        return "Laminadora";
    }

    @Override
    public void processar(placaCircuito placa) {
        System.out.println(" laminando " + placa.getNome() + "...");

        if (verificFalha()) {
            placa.aumentarProbabilidadeFalha(AUMENTO_FALHA);
            System.out.println(" [ERRO] teve uma falha ao laminar " + placa.getNome());
        }
    }
}