package br.com.fiap.models;

import br.com.fiap.enums.TipoAguaReutilizada;

import java.util.Map;

public class SimulacaoReuso {
    private Map<TipoAguaReutilizada, Double> aguaReutilizada;
    private static final double PRECO_LITRO = 0.003; // valor estimado por litro

    public SimulacaoReuso(Map<TipoAguaReutilizada, Double> aguaReutilizada) {
        this.aguaReutilizada = aguaReutilizada;
    }

    public double calcularTotalReutilizado() {
        return aguaReutilizada.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public double calcularEconomiaMensal() {
        return calcularTotalReutilizado() * PRECO_LITRO;
    }

    public double simularEconomia() {
        return calcularEconomiaMensal();
    }

    public double simularEconomia(int meses) {
        return calcularEconomiaMensal() * meses;
    }

    public void exibirResumoSimulacao() {
        System.out.println("=== Simulação de Economia com Reuso de Água ===");
        aguaReutilizada.forEach((tipo, litros) ->
                System.out.printf("- %s: %.2f L\n", tipo.name().replace("_", " "), litros)
        );
        System.out.printf("Total reutilizado: %.2f L\n", calcularTotalReutilizado());
        System.out.printf("Economia mensal estimada: R$ %.2f\n", calcularEconomiaMensal());
    }
}
