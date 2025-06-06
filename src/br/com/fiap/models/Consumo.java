package br.com.fiap.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consumo {
    private LocalDate data;
    private double litrosConsumidos;

    public Consumo(LocalDate data, double litrosConsumidos) {
        this.data = data;
        this.litrosConsumidos = litrosConsumidos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getLitrosConsumidos() {
        return litrosConsumidos;
    }

    public void setLitrosConsumidos(double litrosConsumidos) {
        this.litrosConsumidos = litrosConsumidos;
    }

    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    @Override
    public String toString() {
        return "Data: " + getDataFormatada() + ", Litros consumidos: " + litrosConsumidos + " L";
    }
}