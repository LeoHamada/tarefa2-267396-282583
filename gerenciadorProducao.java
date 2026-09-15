import java.util.ArrayList;

public class gerenciadorProducao {

    private final ArrayList<demanda> demandas;
    private final ArrayList<placaCircuito> produtosFabricados; // armazém
    private final ArrayList<maquinaGenerica> maquinas;
    private final cobre materiaPrima;
    private double budget;

    public gerenciadorProducao(cobre materiaPrima, double budgetInicial, ArrayList<maquinaGenerica> maquinas) {
        this.materiaPrima = materiaPrima;
        this.budget = budgetInicial;
        this.maquinas = maquinas;
        this.demandas = new ArrayList<>();
        this.produtosFabricados = new ArrayList<>();
    }

    public double getBudget() {
        return budget;
    }

    // ---------------- DEMANDAS ----------------

    public void registrarDemanda(demanda d) {
        demandas.add(d);
    }

    private demanda buscarDemanda(String tipoProduto) {
        for (demanda d : demandas) {
            if (d.getTipoProduto().equalsIgnoreCase(tipoProduto)) {
                return d;
            }
        }
        return null;
    }

    public void atualizarDemanda(String tipoProduto, int novaQuantidade) {
        demanda d = buscarDemanda(tipoProduto);
        if (d == null) {
            System.out.println("Demanda não encontrada para: " + tipoProduto);
            return;
        }
        d.atualizarQuantidade(novaQuantidade);
        System.out.println("Demanda atualizada -> " + d);
    }


    private placaCircuito criarProduto(String tipoProduto) {
        switch (tipoProduto) {
            case "Face Única":
                return new placaFaceUnica("Placa Face Única #" + (placaCircuito.getTotalProdutosFabricados() + 1));
            case "Face Dupla":
                return new placaFaceDupla("Placa Face Dupla #" + (placaCircuito.getTotalProdutosFabricados() + 1));
            case "Multilayer Pro":
                return new placaMultilayerPro("Placa Multilayer Pro #" + (placaCircuito.getTotalProdutosFabricados() + 1));
            default:
                return null;
        }
    }

    public void fabricarDemanda(String tipoProduto) {
        demanda d = buscarDemanda(tipoProduto);
        if (d == null) {
            System.out.println("Demanda não encontrada para: " + tipoProduto);
            return;
        }
        if (d.isAtendida()) {
            System.out.println("Demanda de " + tipoProduto + " já está atendida. Nada a fabricar.");
            return;
        }

        placaCircuito placa = criarProduto(tipoProduto);
        if (placa == null) {
            System.out.println("Tipo de produto desconhecido: " + tipoProduto);
            return;
        }

        double materiaPrimaNecessaria = placa.getQuantidadeMateriaPrimaPorUnidade();
        if (!materiaPrima.verificarDisponibilidade(materiaPrimaNecessaria)) {
            System.out.println("[FALHA] Estoque de " + materiaPrima.getNome() + " insuficiente para fabricar " + tipoProduto + "!");
            return;
        }

        double custoProducao = calcularCustoProducao();
        if (custoProducao > budget) {
            System.out.println("[FALHA] Budget insuficiente para operar as máquinas (necessário R$" 
                    + String.format("%.2f", custoProducao) + ", disponível R$" + String.format("%.2f", budget) + ")");
            return;
        }

        materiaPrima.consumir(materiaPrimaNecessaria);
        placa.processar();

        for (maquinaGenerica maquina : maquinas) {
            if (!maquina.estaLigada()) {
                maquina.ligar();
            }
            budget -= maquina.getcustOperacao();
            maquina.processar(placa);
        }

        if ("REJEITADA".equals(placa.getStatus())) {
            System.out.println(">> " + placa.getNome() + " foi descartada após reprovação na inspeção. Matéria-prima e budget consumidos, demanda segue pendente.");
            return;
        }

        placa.setStatus("CONCLUIDO");
        produtosFabricados.add(placa);
        d.atender(1);
        System.out.println(">> " + placa.getNome() + " fabricada com sucesso e enviada ao armazém!");
    }

    private double calcularCustoProducao() {
        double custoTotal = 0.0;
        for (maquinaGenerica maquina : maquinas) {
            custoTotal += maquina.getcustOperacao();
        }
        return custoTotal;
    }

    // ---------------- MATÉRIA-PRIMA ----------------

    public void comprarMateriaPrima(double quantidade) {
        double custoTotal = quantidade * materiaPrima.getCustoPorUnidade();
        if (custoTotal > budget) {
            System.out.println("[FALHA] Budget insuficiente para comprar " + quantidade + " " + materiaPrima.getUnidade()
                    + " de " + materiaPrima.getNome() + " (custo R$" + String.format("%.2f", custoTotal) + ")");
            return;
        }
        budget -= custoTotal;
        materiaPrima.adicionarEstoque(quantidade);
        System.out.println("Compra realizada: " + quantidade + materiaPrima.getUnidade() + " de " + materiaPrima.getNome()
                + " por R$" + String.format("%.2f", custoTotal));
    }

    // ---------------- CONSULTAS ----------------

    public void exibirBudget() {
        System.out.println("BUDGET ATUAL: R$" + String.format("%.2f", budget));
    }

    public void exibirArmazem() {
        System.out.println("--- ARMAZÉM (" + produtosFabricados.size() + " item(ns)) ---");
        if (produtosFabricados.isEmpty()) {
            System.out.println("Nenhum produto fabricado ainda.");
        }
        for (placaCircuito placa : produtosFabricados) {
            System.out.println(placa);
        }
    }

    public void exibirEstoqueMateriaPrima() {
        System.out.println("--- ESTOQUE DE MATÉRIA-PRIMA ---");
        System.out.println(materiaPrima);
    }

    public void exibirDemandas() {
        System.out.println("--- DEMANDAS ---");
        for (demanda d : demandas) {
            System.out.println(d);
        }
    }
}