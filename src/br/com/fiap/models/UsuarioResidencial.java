package br.com.fiap.models;

public class UsuarioResidencial extends Usuario {
    private int numeroMoradores;
    private Cisterna cisterna;

    public int getNumeroMoradores() {
        return numeroMoradores;
    }

    public void setNumeroMoradores(int numeroMoradores) {
        this.numeroMoradores = numeroMoradores;
    }

    // Construtor
    public UsuarioResidencial(String nome, String email, String endereco, int numeroMoradores, Cisterna cisterna) {
        super(nome, email, endereco);
        this.numeroMoradores = numeroMoradores;
        this.cisterna = cisterna;
    }

    //Getters e Setters
    public Cisterna getCisterna() {
        return cisterna;
    }

    public void setCisterna(Cisterna cisterna) {
        this.cisterna = cisterna;
    }

    // Método para calcular o consumo médio de água
    public double calcularConsumoMedio() {
        // Consumo médio por pessoa (exemplo: 150 litros/dia)
        double consumoPorPessoa = 150;
        // Consumo total baseado no número de moradores
        double consumoTotal = consumoPorPessoa * numeroMoradores;

        // Verifica se o usuário tem cisterna (cisterna != null)
        if (cisterna != null) {
            // Se o usuário tem cisterna, calcula a economia
            double economiaCisterna = cisterna.calcularEconomia();
            // Subtrai a economia da cisterna do consumo total
            consumoTotal -= economiaCisterna;
        }

        return consumoTotal;  // Retorna o consumo total após a economia da cisterna, se houver
    }
}
