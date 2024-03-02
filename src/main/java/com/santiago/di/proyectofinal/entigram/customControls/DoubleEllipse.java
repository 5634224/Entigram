package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;

public class DoubleEllipse extends ArrastrableControl {
    private Ellipse outerEllipse;
    private Ellipse innerEllipse;
    private LabelControl label;

    public DoubleEllipse(Pane contenedor) {
        super(contenedor);

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(75);

        // Crea la elipse interior
        innerEllipse = new Ellipse();
        innerEllipse.setFill(Color.WHITE); // Sin color de fondo
        innerEllipse.setStroke(Color.BLACK); // Borde negro

        // Crea la elipse exterior
        outerEllipse = new Ellipse();
        outerEllipse.setFill(Color.WHITE); // Sin color de fondo
        outerEllipse.setStroke(Color.BLACK); // Borde negro

        // Label
        label = new LabelControl(contenedor, "Prueba");
        label.get().setFont(Font.font(14.0));
        label.get().setTextFill(Color.BLACK);

        getChildren().addAll(outerEllipse, innerEllipse, label);
    }

    @Override
    public void layoutChildren() {
        // Redimensiona la elipse exterior
        outerEllipse.setCenterX(getWidth() / 2);
        outerEllipse.setCenterY(getHeight() / 2);
        outerEllipse.setRadiusX(getWidth() / 2);
        outerEllipse.setRadiusY(getHeight() / 2);

        // Redimensiona la elipse interior
        final double INNER_ELLIPSE_RATIO = 0.9; // Ajusta este valor para cambiar el tamaño de la elipse interior
        innerEllipse.setCenterX(getWidth() / 2);
        innerEllipse.setCenterY(getHeight() / 2);
        innerEllipse.setRadiusX(outerEllipse.getRadiusX() * INNER_ELLIPSE_RATIO);
        innerEllipse.setRadiusY(outerEllipse.getRadiusY() * INNER_ELLIPSE_RATIO);

        // Centrar el Label en el control
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
        requestLayout();
    }

    public double getEllipseWidth() {
        return outerEllipse.getRadiusX() * 2;
    }

    public void setEllipseWidth(double width) {
        setWidth(width);
        requestLayout();
    }

    public double getEllipseHeight() {
        return outerEllipse.getRadiusY() * 2;
    }

    public void setEllipseHeight(double height) {
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