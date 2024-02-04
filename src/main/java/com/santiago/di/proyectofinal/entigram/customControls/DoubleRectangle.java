package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DoubleRectangle extends Control {

    private Rectangle outerRectangle;
    private Rectangle innerRectangle;
    private Label label;

    public DoubleRectangle() {
        super();
        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(75);

        // Crea un doble rectangulo
        outerRectangle = new Rectangle();
        innerRectangle = new Rectangle();

        // Sin color de fondo
        outerRectangle.setFill(Color.WHITE);
        innerRectangle.setFill(Color.WHITE);

        // Borde negro
        outerRectangle.setStroke(Color.BLACK);
        innerRectangle.setStroke(Color.BLACK);

        // Label
        label = new Label("Prueba");
        label.setFont(Font.font(14.0));
        label.setTextFill(Color.BLACK);

        // Añade el rectangulo y el label al control
        getChildren().addAll(outerRectangle, innerRectangle, label);
    }

    @Override
    public void layoutChildren() {
        double gap = 3.5; // Define un gap de la decima parte del total del ancho
        outerRectangle.setWidth(getWidth());
        outerRectangle.setHeight(getHeight());

        innerRectangle.setLayoutX(gap);
        innerRectangle.setLayoutY(gap);
        innerRectangle.setWidth(getWidth() - 2 * gap);
        innerRectangle.setHeight(getHeight() - 2 * gap);

        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
        requestLayout();
    }

    public double getRectangleWidth() {
        return getWidth();
    }

    public double getRectangleHeight() {
        return getHeight();
    }

    public void setRectangleWidth(double width) {
        setWidth(width);
        requestLayout();
    }

    public void setRectangleHeight(double height) {
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
        return new SkinBase<DoubleRectangle>(this) {
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