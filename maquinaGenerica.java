import java.util.Random;

public abstract class maquinaGenerica {

    private final String nome;
    private boolean ligada;
    private int capacidadeMax;
    private double probabFalha;
    private double custOperacao;
    private Random aleatoriedade = new Random();

    public maquinaGenerica(String nome, boolean ligada, int capacidadeMax, double probabFalha, double costOperacao){

        this.nome = nome;
        this.ligada = ligada;
        this.capacidadeMax = capacidadeMax;
        this.probabFalha = probabFalha;
        this.custOperacao = costOperacao;
    }

    public abstract void processar(placaCircuito placa);

    public abstract String getTipo();

    public void ligar(){
        if (ligada) {
            System.out.println( "ja esta ligada");
        }
        else {
            ligada = true;
            System.out.println(" foi ligada");
        }
    }
    
    public void desligar(){
        if(ligada) {
            ligada = false;
            System.out.println(" foi desligada");
        } else {
            System.out.println(" ja esta desligada");
        }
    }
    
    public double getcustOperacao(){
        return custOperacao;
    }

    public String getnome(){
        return nome;
    }

    public boolean verificFalha() {
        double aleatorio = aleatoriedade.nextDouble();
        return aleatorio < probabFalha;
    }

    @Override
    public String toString(){
        return String.format("%s [%s] Capacidade Máxima = %d Probabilidade de Falha = %.2f Custo = %.2f"
            , nome, getTipo(), capacidadeMax, probabFalha, custOperacao);
    }

}
