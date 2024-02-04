package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;

public class DoubleEllipse extends Control {

    private Ellipse ellipse;
    private Label label;

    public DoubleEllipse() {
        super();

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(75);

        // Crea una elipse
        ellipse = new Ellipse();

        // Sin color de fondo
        ellipse.setFill(Color.WHITE);

        // Borde negro
        ellipse.setStroke(Color.BLACK);

        // Label
        label = new Label("Prueba");
        label.setFont(Font.font(14.0));
        label.setTextFill(Color.BLACK);

        getChildren().addAll(ellipse, label);
    }

    @Override
    public void layoutChildren() {
        ellipse.setCenterX(getWidth() / 2);
        ellipse.setCenterY(getHeight() / 2);
        ellipse.setRadiusX(getWidth() / 2);
        ellipse.setRadiusY(getHeight() / 2);

        // Centrar el Label en el control
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
        setWidth(width);
        setHeight(height);
        requestLayout();
    }

    public double getEllipseWidth() {
        return ellipse.getRadiusX() * 2;
    }

    public void setEllipseWidth(double width) {
        ellipse.setRadiusX(width / 2);
        requestLayout();
    }

    public double getEllipseHeight() {
        return ellipse.getRadiusY() * 2;
    }

    public void setEllipseHeight(double height) {
        ellipse.setRadiusY(height / 2);
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
        return new SkinBase<DoubleEllipse>(this) {
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