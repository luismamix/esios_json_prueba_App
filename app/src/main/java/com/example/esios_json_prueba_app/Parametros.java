package com.example.esios_json_prueba_app;

import java.io.Serializable;

public class Parametros implements Serializable {
    private String dia;
    private String zona;
    private double preciomedio;

    public double getPreciomedio() {
        return preciomedio;
    }

    public void setPreciomedio(double preciomedio) {
        this.preciomedio = preciomedio;
    }

    public Parametros() {
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
