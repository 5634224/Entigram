package com.santiago.di.proyectofinal.entigram.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Atributo {

    private StringProperty nombre;
    private StringProperty tipoDato;
    private int longitud;

    public Atributo(String nombre, String tipoDato, int longitud) {
        this.nombre = new SimpleStringProperty(nombre);
        this.tipoDato = new SimpleStringProperty(tipoDato);
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.setValue(nombre);
    }

    public String getTipoDato() {
        return tipoDato.get();
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato.setValue(tipoDato);
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}