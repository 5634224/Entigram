package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CustomRectangle extends Control {

    private Label label;
    private Rectangle customRectangle;

    public CustomRectangle() {
        super();
        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(75);

        // Crea un rectangulo
        customRectangle = new Rectangle();

        // Sin color de fondo
        customRectangle.setFill(Color.WHITE);

        // Borde negro
        customRectangle.setStroke(Color.BLACK);

        // Label
        label = new Label("Prueba");
        label.setFont(Font.font(14.0));
        label.setTextFill(Color.BLACK);

        // Añade el rectangulo y el label al control
        getChildren().addAll(customRectangle, label);
    }

    @Override
    public void layoutChildren() {
        customRectangle.setWidth(getWidth());
        customRectangle.setHeight(getHeight());

        label.applyCss();
        label.layout();

        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
//        customRectangle.setWidth(width);
//        customRectangle.setHeight(height);
        requestLayout();
    }

    public double getRectangleWidth() {
        return customRectangle.getWidth();
    }

    public void setRectangleWidth(double width) {
//        customRectangle.setWidth(width);
        setWidth(width);
        requestLayout();
    }

    public double getRectangleHeight() {
        return customRectangle.getHeight();
    }

    public void setRectangleHeight(double height) {
//        customRectangle.setHeight(height);
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
        return new SkinBase<CustomRectangle>(this) {
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