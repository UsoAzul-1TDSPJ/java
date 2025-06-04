package br.com.fiap.models;

import br.com.fiap.enums.NivelCisterna;


public class Cisterna {
    private double capacidadeMaxima;
    private double nivelAtual;
    private double alertaMinimo;
    private double alertaMaximo;

    // Construtor
    public Cisterna(double capacidadeMaxima, double alertaMinimo, double alertaMaximo) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.nivelAtual = capacidadeMaxima;  // Assume que a cisterna começa cheia
        this.alertaMinimo = alertaMinimo;
        this.alertaMaximo = alertaMaximo;
    }


    // Métodos para adicionar e usar água
    public void adicionarAgua(double litros) {
        if (nivelAtual + litros <= capacidadeMaxima) {
            nivelAtual += litros;
        } else {
            nivelAtual = capacidadeMaxima;  // Não ultrapassa a capacidade
        }
    }

    public void usarAgua(double litros) {
        if (nivelAtual - litros >= 0) {
            nivelAtual -= litros;
        } else {
            nivelAtual = 0;  // Não permite usar mais água do que tem
        }
    }

    // Método para verificar alertas
    public String verificarAlertas() {
        if (nivelAtual <= alertaMinimo) {
            return "Alerta: Nível de água muito baixo!";
        } else if (nivelAtual >= alertaMaximo) {
            return "Alerta: Nível de água muito alto!";
        } else {
            return "Nível de água normal.";
        }
    }

    // Método para calcular a economia com base no nível de água
    public double calcularEconomia() {
        // Por exemplo, a economia é uma porcentagem da capacidade máxima da cisterna
        return capacidadeMaxima * 0.30;  // 30% da capacidade é reutilizada
    }

    // Método para obter o nível da cisterna com base no valor atual
    public NivelCisterna getNivelCisterna() {
        if (nivelAtual <= alertaMinimo) {
            return NivelCisterna.NORMAL;  // Nível está normal
        } else if (nivelAtual >= alertaMaximo) {
            return NivelCisterna.ACIMA_DO_MAXIMO;  // Nível está acima do máximo
        } else {
            return NivelCisterna.ABAIXO_DO_MAXIMO;  // Nível está abaixo do máximo
        }
    }

    //Getters e Setters
    public double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(double capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public double getNivelAtual() {
        return nivelAtual;
    }

    public void setNivelAtual(double nivelAtual) {
        this.nivelAtual = nivelAtual;
    }

    public double getAlertaMinimo() {
        return alertaMinimo;
    }

    public void setAlertaMinimo(double alertaMinimo) {
        this.alertaMinimo = alertaMinimo;
    }

    public double getAlertaMaximo() {
        return alertaMaximo;
    }

    public void setAlertaMaximo(double alertaMaximo) {
        this.alertaMaximo = alertaMaximo;
    }
}
