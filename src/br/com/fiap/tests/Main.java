package br.com.fiap.tests;

import br.com.fiap.enums.TipoAguaReutilizada;
import br.com.fiap.models.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bem-vindo ao Sistema de Gestão da Cisterna ===");

        // Criar cisterna com parâmetros iniciais
        System.out.print("Informe a capacidade máxima da cisterna (litros): ");
        double capacidade = lerDouble(scanner);

        System.out.print("Informe o nível mínimo para alerta (litros): ");
        double alertaMin = lerDouble(scanner);

        System.out.print("Informe o nível máximo para alerta (litros): ");
        double alertaMax = lerDouble(scanner);

        Cisterna cisterna = new Cisterna(capacidade, alertaMin, alertaMax);

        // Criar usuário residencial
        System.out.print("Informe o nome do usuário residencial: ");
        String nome = scanner.nextLine();

        System.out.print("Informe o email do usuário: ");
        String email = scanner.nextLine();

        System.out.print("Informe o endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Informe o número de moradores: ");
        int moradores = lerInt(scanner);

        UsuarioResidencial usuario = new UsuarioResidencial(nome, email, endereco, moradores, cisterna);

        Map<TipoAguaReutilizada, Double> aguaReuso = new HashMap<>();

        List<Consumo> consumos = new ArrayList<>();

        boolean sair = false;
        while (!sair) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Adicionar água na cisterna");
            System.out.println("2. Usar água da cisterna");
            System.out.println("3. Verificar alertas da cisterna");
            System.out.println("4. Cadastrar tipo e quantidade de água reutilizada");
            System.out.println("5. Adicionar consumo diário");
            System.out.println("6. Mostrar resumo de simulação de reúso");
            System.out.println("7. Gerar relatório completo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInt(scanner);

            switch (opcao) {
                case 1:
                    System.out.print("Digite a quantidade de litros para adicionar: ");
                    double addLitros = lerDouble(scanner);
                    cisterna.adicionarAgua(addLitros);
                    System.out.println("Nível atual da cisterna: " + cisterna.getNivelAtual() + " litros.");
                    break;

                case 2:
                    System.out.print("Digite a quantidade de litros para usar: ");
                    double usarLitros = lerDouble(scanner);
                    cisterna.usarAgua(usarLitros);
                    System.out.println("Nível atual da cisterna: " + cisterna.getNivelAtual() + " litros.");
                    break;

                case 3:
                    System.out.println(cisterna.verificarAlertas());
                    System.out.println("Nível da cisterna: " + cisterna.getNivelAtual() + " litros.");
                    System.out.println("Capacidade máxima: " + cisterna.getCapacidadeMaxima() + " litros.");
                    System.out.println("Nível mínimo de alerta: " + cisterna.getAlertaMinimo() + " litros.");
                    System.out.println("Nível máximo de alerta: " + cisterna.getAlertaMaximo() + " litros.");
                    System.out.println("Estado da cisterna: " + cisterna.getNivelCisterna());
                    break;

                case 4:
                    System.out.println("Tipos de água reutilizada disponíveis:");
                    for (TipoAguaReutilizada tipo : TipoAguaReutilizada.values()) {
                        System.out.println(tipo.ordinal() + 1 + ". " + tipo);
                    }
                    System.out.print("Escolha o tipo (número): ");
                    int tipoEscolhido = lerInt(scanner);
                    if (tipoEscolhido < 1 || tipoEscolhido > TipoAguaReutilizada.values().length) {
                        System.out.println("Tipo inválido.");
                    } else {
                        TipoAguaReutilizada tipoSelecionado = TipoAguaReutilizada.values()[tipoEscolhido - 1];
                        System.out.print("Informe a quantidade de litros reutilizados para " + tipoSelecionado + ": ");
                        double quantidade = lerDouble(scanner);
                        aguaReuso.put(tipoSelecionado, quantidade);
                        System.out.println("Água reutilizada cadastrada.");
                    }
                    break;

                case 5:
                    System.out.print("Digite a data do consumo (formato YYYY-MM-DD): ");
                    String dataStr = scanner.nextLine();
                    LocalDate data;
                    try {
                        data = LocalDate.parse(dataStr);
                    } catch (DateTimeParseException e) {
                        System.out.println("Data inválida.");
                        break;
                    }
                    System.out.print("Digite a quantidade de litros consumidos neste dia: ");
                    double litrosConsumidos = lerDouble(scanner);
                    consumos.add(new Consumo(data, litrosConsumidos));
                    System.out.println("Consumo adicionado.");
                    break;

                case 6:
                    SimulacaoReuso simulacao = new SimulacaoReuso(aguaReuso);
                    simulacao.exibirResumoSimulacao();
                    break;

                case 7:
                    SimulacaoReuso simulacaoRelatorio = new SimulacaoReuso(aguaReuso);
                    Relatorio relatorio = new Relatorio(usuario, consumos, simulacaoRelatorio);
                    System.out.println("\n--- Relatório Completo ---");
                    System.out.println(relatorio.gerarResumo());
                    break;

                case 0:
                    sair = true;
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }

        scanner.close();
    }

    private static double lerDouble(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input.replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite um número válido: ");
            }
        }
    }

    private static int lerInt(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite um número inteiro válido: ");
            }
        }
    }
}