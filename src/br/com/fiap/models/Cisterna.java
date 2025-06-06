package br.com.fiap.models;

import br.com.fiap.enums.NivelCisterna;

public class Cisterna {
    private double capacidadeMaxima;
    private double nivelAtual;
    private double alertaMinimo;
    private double alertaMaximo;

    public Cisterna(double capacidadeMaxima, double alertaMinimo, double alertaMaximo) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.nivelAtual = 0.0;  // Começa vazia
        this.alertaMinimo = alertaMinimo;
        this.alertaMaximo = alertaMaximo;
    }

    public void adicionarAgua(double litros) {
        if (nivelAtual + litros <= capacidadeMaxima) {
            nivelAtual += litros;
        } else {
            nivelAtual = capacidadeMaxima;
        }
    }

    // Sobrecarga do método adicionarAgua
    public void adicionarAgua(double litros, boolean ignorarLimite) {
        if (ignorarLimite) {
            nivelAtual += litros;
        } else {
            adicionarAgua(litros);
        }
    }

    public void usarAgua(double litros) {
        if (nivelAtual - litros >= 0) {
            nivelAtual -= litros;
        } else {
            nivelAtual = 0;
        }
    }

    public String verificarAlertas() {
        if (nivelAtual <= alertaMinimo) {
            return "Alerta: Nível de água muito baixo!";
        } else if (nivelAtual >= alertaMaximo) {
            return "Alerta: Nível de água muito alto!";
        } else {
            return "Nível de água normal.";
        }
    }

    public double calcularEconomia() {
        return capacidadeMaxima * 0.30; // 30% da capacidade é economizada, exemplo
    }

    public NivelCisterna getNivelCisterna() {
        if (nivelAtual <= alertaMinimo) {
            return NivelCisterna.ABAIXO_DO_MINIMO;
        } else if (nivelAtual >= alertaMaximo) {
            return NivelCisterna.ACIMA_DO_MAXIMO;
        } else {
            return NivelCisterna.NORMAL;
        }
    }

    // Getters e Setters

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
