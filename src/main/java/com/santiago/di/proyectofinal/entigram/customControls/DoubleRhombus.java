package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

public class DoubleRhombus extends Control {

    private Polygon outerPolygon;
    private Polygon innerPolygon;
    private Label label;

    public DoubleRhombus() {
        super();
        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(50);

        // Crea un rombo doble
        outerPolygon = new Polygon();
        innerPolygon = new Polygon();

        // Sin color de fondo
        outerPolygon.setFill(Color.WHITE);
        innerPolygon.setFill(Color.WHITE);

        // Borde negro
        outerPolygon.setStroke(Color.BLACK);
        innerPolygon.setStroke(Color.BLACK);

        // Label
        label = new Label("Prueba");
        label.setFont(Font.font(14.0));
        label.setTextFill(Color.BLACK);

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
        return label.getText();
    }

    public void setLabelText(String text) {
        label.setText(text);
        requestLayout();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SkinBase<DoubleRhombus>(this) {
            @Override
            protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
                // Layout logic goes here
            }

            @Override
            public void dispose() {
                // Cleanup logic goes here
            }
        };
    }
}