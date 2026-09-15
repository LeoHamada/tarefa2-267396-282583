import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final String TIPO_FACE_UNICA = "Face Única";
    private static final String TIPO_FACE_DUPLA = "Face Dupla";
    private static final String TIPO_MULTILAYER = "Multilayer Pro";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        cobre Cobre = new cobre(1, 500.0, 0.5);

        ArrayList<maquinaGenerica> maquinas = new ArrayList<>();
        maquinas.add(new maquinaLaminadora("Laminadora-01", true));
        maquinas.add(new maquinaSoldagem("Soldadora-01", true));
        maquinas.add(new maquinaInspecaoOptica("Inspetora-01", true));

        gerenciadorProducao gerenciador = new gerenciadorProducao(Cobre, 1000.0, maquinas);

        gerenciador.registrarDemanda(new demanda(TIPO_FACE_UNICA, 0));
        gerenciador.registrarDemanda(new demanda(TIPO_FACE_DUPLA, 0));
        gerenciador.registrarDemanda(new demanda(TIPO_MULTILAYER, 0));

        exibirIntroducao();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu(gerenciador);
            opcao = lerOpcao(scanner);

            switch (opcao) {
                case 1:
                    atualizarDemandaMenu(scanner, gerenciador, TIPO_FACE_UNICA);
                    break;
                case 2:
                    atualizarDemandaMenu(scanner, gerenciador, TIPO_FACE_DUPLA);
                    break;
                case 3:
                    atualizarDemandaMenu(scanner, gerenciador, TIPO_MULTILAYER);
                    break;
                case 4:
                    gerenciador.fabricarDemanda(TIPO_FACE_UNICA);
                    break;
                case 5:
                    gerenciador.fabricarDemanda(TIPO_FACE_DUPLA);
                    break;
                case 6:
                    gerenciador.fabricarDemanda(TIPO_MULTILAYER);
                    break;
                case 7:
                    gerenciador.exibirArmazem();
                    break;
                case 8:
                    gerenciador.exibirEstoqueMateriaPrima();
                    break;
                case 9:
                    comprarMateriaPrimaMenu(scanner, gerenciador);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha um número do menu.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void exibirIntroducao() {
        System.out.println("=======================================================");
        System.out.println("                      PCB FH Factory                   ");
        System.out.println("=======================================================");
        System.out.println("Bem-vindo(a) à linha de produção automatizada!");
        System.out.println("Aqui você gerencia matéria-prima, máquinas, demandas");
        System.out.println("e o budget da fábrica para produzir placas de circuito");
        System.out.println("com diferentes níveis de complexidade e qualidade.");
        System.out.println("                                                   ");
    }

    private static void exibirMenu(gerenciadorProducao gerenciador) {
        System.out.println("-------------------------------------------------------");
        gerenciador.exibirBudget();
        System.out.println("-------------------------------------------------------");
        System.out.println("ATUALIZAR DEMANDAS");
        System.out.println("1 - Atualizar demanda de Placa Face Única");
        System.out.println("2 - Atualizar demanda de Placa Face Dupla");
        System.out.println("3 - Atualizar demanda de Placa Multilayer Pro");
        System.out.println("                                                   ");
        System.out.println("FABRICAR");
        System.out.println("4 - Fabricar Placa Face Única");
        System.out.println("5 - Fabricar Placa Face Dupla");
        System.out.println("6 - Fabricar Placa Multilayer Pro");
        System.out.println("                                                   ");
        System.out.println("CONSULTAR");
        System.out.println("7 - Ver armazém");
        System.out.println("8 - Ver estoque de matéria-prima");
        System.out.println("                                                   ");
        System.out.println("COMPRAR MATÉRIA-PRIMA");
        System.out.println("9 - Comprar matéria-prima");
        System.out.println("0 - SAIR");
        System.out.println("                                                   ");
        System.out.print("ESCOLHA: ");
    }

    private static int lerOpcao(Scanner scanner) {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o buffer
            return opcao;
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite apenas números.");
            scanner.nextLine(); // descarta a entrada inválida
            return -1;
        }
    }

    private static double lerDouble(Scanner scanner) {
        while (true) {
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                if (valor < 0) {
                    System.out.println("O valor não pode ser negativo. Tente novamente: ");
                    continue;
                }
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números. Tente novamente: ");
                scanner.nextLine();
            }
        }
    }

    private static int lerInt(Scanner scanner) {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                if (valor < 0) {
                    System.out.println("O valor não pode ser negativo. Tente novamente: ");
                    continue;
                }
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números. Tente novamente: ");
                scanner.nextLine();
            }
        }
    }

    private static void atualizarDemandaMenu(Scanner scanner, gerenciadorProducao gerenciador, String tipoProduto) {
        System.out.print("Nova quantidade de " + tipoProduto + " demandada: ");
        int novaQuantidade = lerInt(scanner);
        gerenciador.atualizarDemanda(tipoProduto, novaQuantidade);
    }

    private static void comprarMateriaPrimaMenu(Scanner scanner, gerenciadorProducao gerenciador) {
        System.out.print("Quantidade de matéria-prima a comprar (cm^2): ");
        double quantidade = lerDouble(scanner);
        gerenciador.comprarMateriaPrima(quantidade);
    }
}