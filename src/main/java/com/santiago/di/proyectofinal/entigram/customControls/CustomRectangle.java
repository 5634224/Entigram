package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CustomRectangle extends ArrastrableControl {
    private LabelControl label;
    private Rectangle customRectangle;

    public CustomRectangle(Pane contenedor) {
        super(contenedor);

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(75);

        // Crea un rectangulo
        customRectangle = new Rectangle();
        customRectangle.setFill(Color.WHITE); // Sin color de fondo
        customRectangle.setStroke(Color.BLACK); // Borde negro

        // Label
        label = new LabelControl(contenedor,"Prueba");
        label.get().setFont(Font.font(14.0));
        label.get().setTextFill(Color.BLACK);

        // Añade el rectangulo y el label al control
        getChildren().addAll(customRectangle, label);
    }

    @Override
    public void layoutChildren() {
        customRectangle.setWidth(getWidth());
        customRectangle.setHeight(getHeight());

        label.applyCss();
        label.layout();

        // Centrar el Label en el control
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
            System.err.println("No se puede eliminar el control del contenedor");
        }
    }

    @Override
    public void editarControl(Event event) {
        label.editarControl(event);
    }
}