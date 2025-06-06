package br.com.fiap.models;

import java.util.List;

public class Relatorio {
    private UsuarioResidencial usuario;
    private List<Consumo> consumos;
    private SimulacaoReuso simulacao;

    public Relatorio(UsuarioResidencial usuario, List<Consumo> consumos, SimulacaoReuso simulacao) {
        this.usuario = usuario;
        this.consumos = consumos;
        this.simulacao = simulacao;
    }

    public String gerarResumo() {
        StringBuilder resumo = new StringBuilder();
        resumo.append("=== Relatório de Consumo e Reuso ===\n");
        resumo.append("Usuário: ").append(usuario.getNome()).append("\n");
        resumo.append("Endereço: ").append(usuario.getEndereco()).append("\n");
        resumo.append("Número de moradores: ").append(usuario.getNumeroMoradores()).append("\n\n");

        resumo.append("Consumo registrado:\n");
        for (Consumo consumo : consumos) {
            resumo.append("- ").append(consumo.getDataFormatada())
                    .append(": ").append(consumo.getLitrosConsumidos()).append(" L\n");
        }

        resumo.append("\nTotal de consumo: ").append(calcularTotalConsumo()).append(" L\n");
        resumo.append("Total reutilizado: ").append(simulacao.calcularTotalReutilizado()).append(" L\n");
        resumo.append("Economia estimada: R$ ").append(String.format("%.2f", simulacao.simularEconomia())).append("\n");

        return resumo.toString();
    }

    public double calcularTotalConsumo() {
        return consumos.stream().mapToDouble(Consumo::getLitrosConsumidos).sum();
    }

    public double calcularTotalEconomia() {
        return simulacao.simularEconomia();
    }
}
