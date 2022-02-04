package com.example.esios_json_prueba_app;

public class Electrodomestico {
    private String tipo_electrodomestico;
    private double potencia;
    private double horas;

    public Electrodomestico() {
    }

    public String getTipo_electrodomestico() {
        return tipo_electrodomestico;
    }

    public void setTipo_electrodomestico(String tipo_electrodomestico) {
        this.tipo_electrodomestico = tipo_electrodomestico;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public double consumoDiario (){
        return (potencia / 1000) * horas;
    }

    public double consumoMensual () {
        return consumoDiario() * 30;
    }

    public double gastoDiario(double precio_medio_dia_kwh){
        return consumoDiario() * precio_medio_dia_kwh;
    }

    public double gastoMensual(double precio_medio_mes_kwh){
        return consumoMensual() * precio_medio_mes_kwh;
    }
}
