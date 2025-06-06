package br.com.fiap.models;

public class UsuarioResidencial extends Usuario {
    private int numeroMoradores;
    private Cisterna cisterna;

    // Construtor
    public UsuarioResidencial(String nome, String email, String endereco, int numeroMoradores, Cisterna cisterna) {
        super(nome, email, endereco);
        this.numeroMoradores = numeroMoradores;
        this.cisterna = cisterna;
    }

    // Getters e Setters
    public int getNumeroMoradores() {
        return numeroMoradores;
    }

    public void setNumeroMoradores(int numeroMoradores) {
        this.numeroMoradores = numeroMoradores;
    }

    public Cisterna getCisterna() {
        return cisterna;
    }

    public void setCisterna(Cisterna cisterna) {
        this.cisterna = cisterna;
    }

    // Sobrescrita do método exibirInformacoes()
    @Override
    public String exibirInformacoes() {
        return super.exibirInformacoes() + ", Nº de moradores: " + numeroMoradores;
    }

    // Método para calcular o consumo médio de água
    public double calcularConsumoMedio() {
        double consumoPorPessoa = 150; // litros/dia (exemplo)
        double consumoTotal = consumoPorPessoa * numeroMoradores;

        if (cisterna != null) {
            double economiaCisterna = cisterna.calcularEconomia();
            consumoTotal -= economiaCisterna;
        }

        return consumoTotal;
    }
}
