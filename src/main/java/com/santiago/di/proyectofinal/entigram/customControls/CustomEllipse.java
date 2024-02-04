package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;

public class CustomEllipse extends Control {

    private Ellipse ellipse;
    private Label label;

    public CustomEllipse() {
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

        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
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

    public void setDashedBorder(boolean dashed) {
        ObservableList<Double> strokeDashArray = ellipse.getStrokeDashArray();
        strokeDashArray.clear();
        if (dashed) {
            strokeDashArray.addAll(10.0, 10.0); // Este patrón crea una línea discontinua con segmentos de 10px y espacios de 10px
        }
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SkinBase<CustomEllipse>(this) {
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