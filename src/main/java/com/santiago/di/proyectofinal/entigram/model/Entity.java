package com.santiago.di.proyectofinal.entigram.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;
import java.util.ArrayList;

public class Entity {
    private StringProperty nombre;
    private StringProperty descripcion;
    private List<Atributo> atributos;

    public Entity(String nombre, String descripcion) {
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.atributos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty getNombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.setValue(nombre);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty getDescripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.setValue(descripcion);
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void addAtributo(Atributo atributo) {
        this.atributos.add(atributo);
    }
}
