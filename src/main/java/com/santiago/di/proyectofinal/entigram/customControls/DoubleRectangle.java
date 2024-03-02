package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DoubleRectangle extends ArrastrableControl {
    public static final double STROKE_LINE_WIDTH = 2.0;
    private Rectangle outerRectangle;
    private Rectangle innerRectangle;
    private LabelControl label;

    public DoubleRectangle(Pane contenedor) {
        super(contenedor);

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(75);

        // Crea el rectángulo interior
        innerRectangle = new Rectangle();
        innerRectangle.setStroke(Color.BLACK); // Sin color de fondo
        innerRectangle.setFill(Color.WHITE); // Borde negro
        innerRectangle.setStrokeWidth(STROKE_LINE_WIDTH);

        // Crea el rectángulo exterior
        outerRectangle = new Rectangle();
        outerRectangle.setFill(Color.WHITE); // Sin color de fondo
        outerRectangle.setStroke(Color.BLACK); // Borde negro
        outerRectangle.setStrokeWidth(STROKE_LINE_WIDTH);

        // Label
        label = new LabelControl(this, "Prueba");
        label.get().setFont(Font.font(14.0));
        label.get().setTextFill(Color.BLACK);

        // Añade el rectangulo y el label al control
        getChildren().addAll(outerRectangle, innerRectangle, label);
    }

    /**
     * Método que define cómo se redimensionarán los componentes del ArrastrableControl cuando este cambie en el lienzo
     */
    @Override
    public void layoutChildren() {
        super.layoutChildren();
        double gap = 5; // Define un gap

        // Redimensiona el rectangulo exterior
        outerRectangle.setWidth(getWidth());
        outerRectangle.setHeight(getHeight());

        // Redimensiona el rectangulo interior
        innerRectangle.setLayoutX(gap);
        innerRectangle.setLayoutY(gap);
        innerRectangle.setWidth(getWidth() - 2 * gap);
        innerRectangle.setHeight(getHeight() - 2 * gap);

        // Centrar el Label en el control
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
//        requestLayout();
        layoutChildren();
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