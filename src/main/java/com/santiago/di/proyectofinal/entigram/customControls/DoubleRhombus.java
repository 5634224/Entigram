package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

public class DoubleRhombus extends ArrastrableControl {
    private Polygon outerPolygon;
    private Polygon innerPolygon;
    private LabelControl label;

    public DoubleRhombus(Pane contenedor) {
        super(contenedor);

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(50);

        // Crea el rombo interior
        innerPolygon = new Polygon();
        innerPolygon.setFill(Color.WHITE); // Sin color de fondo
        innerPolygon.setStroke(Color.BLACK); // Borde negro

        // Crea el rombo exterior
        outerPolygon = new Polygon();
        outerPolygon.setFill(Color.WHITE); // Sin color de fondo
        outerPolygon.setStroke(Color.BLACK); // Borde negro

        // Label
        label = new LabelControl(contenedor,"Prueba");
        label.get().setFont(Font.font(14.0));
        label.get().setTextFill(Color.BLACK);

        // Añade el rectangulo y el label al control
        getChildren().addAll(outerPolygon, innerPolygon, label);
    }

    @Override
    public void layoutChildren() {
        // Dibuja el rombo exterior
        outerPolygon.getPoints().clear();
        outerPolygon.getPoints().addAll(
            0.0, getHeight() / 2,
            getWidth() / 2, 0.0,
            getWidth(), getHeight() / 2,
            getWidth() / 2, getHeight()
        );

        // Dibuja el rombo interior
        final double GAP_WIDTH = getWidth() / 12.5;
        final double GAP_HEIGHT = getHeight() / 12.5;
        innerPolygon.getPoints().clear();
        innerPolygon.getPoints().addAll(
                GAP_WIDTH, getHeight() / 2,
                getWidth() / 2, GAP_HEIGHT,
                getWidth() - GAP_WIDTH, getHeight() / 2,
                getWidth() / 2, getHeight() - GAP_HEIGHT
        );

        // Centra el label dentro del rombo
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
        // Actualiza el tamaño del rombo
        requestLayout();
    }

    public double getRhombusWidth() {
        return getWidth();
    }

    public void setRhombusWidth(double width) {
        setWidth(width);
        requestLayout();
    }

    public double getRhombusHeight() {
        return getHeight();
    }

    public void setRhombusHeight(double height) {
        setHeight(height);
        requestLayout();
    }

    public String getLabelText() {
        return label.get().getText();
    }

    public void setLabelText(String text) {
        label.get().setText(text);
        requestLayout();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CustomControlSkin(this);
    }

    @Override
    public void eliminarControlDelContenedor(ActionEvent event) {
        try {
            ((Pane) getContenedor()).getChildren().remove(this);
        } catch (ClassCastException e) {
            System.out.println("No se puede eliminar el control del contenedor");
        }
    }

    @Override
    public void editarControl(Event event) {
        label.editarControl(event);
    }
}