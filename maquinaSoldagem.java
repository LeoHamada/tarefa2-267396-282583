public class maquinaSoldagem extends maquinaGenerica {

    private static final int CAPACIDADE_MAXIMA = 20;
    private static final double PROBABILIDADE_FALHA = 0.3;
    private static final double CUSTO_OPERACAO = 15.0;
    private static final double AUMENTO_FALHA = 0.3; 

    public maquinaSoldagem(String nome, boolean ligada) {
        super(nome, ligada, CAPACIDADE_MAXIMA, PROBABILIDADE_FALHA, CUSTO_OPERACAO);
    }

    @Override
    public String getTipo() {
        return "Solda";
    }

    @Override
    public void processar(placaCircuito placa) {
        System.out.println(getnome() + " Soldando " + placa.getNome() + "...");

        if (verificFalha()) {
            placa.aumentarProbabilidadeFalha(AUMENTO_FALHA);
            System.out.println(" [ERRO] Falha de Soldagem " + placa.getNome());
        }
    }
    
}
