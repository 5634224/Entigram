package com.santiago.di.proyectofinal.entigram.customControls;

import com.santiago.di.proyectofinal.entigram.JavaFXUtil;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.Optional;

public class DoubleRectangle extends ArrastrableControl {
    public static final double STROKE_LINE_WIDTH = 2.0;
    private Rectangle outerRectangle;
    private Rectangle innerRectangle;
    private Label label;
    private final double gap = 4; // Define un gap

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
        label = new Label("Entidad débil");
        label.getStyleClass().add("texto-componentes");

        // Añade el rectangulo y el label al control
        getChildren().addAll(outerRectangle, innerRectangle, label);

        // Bindea el tamaño del label con el tamaño del rectángulo
        innerRectangle.widthProperty().bind(label.widthProperty().add(20));
        innerRectangle.heightProperty().bind(label.heightProperty().add(20));

        outerRectangle.widthProperty().bind(innerRectangle.widthProperty().add(2*gap));
        outerRectangle.heightProperty().bind(innerRectangle.heightProperty().add(2*gap));
    }

    /**
     * Método que define cómo se redimensionarán los componentes del ArrastrableControl cuando este cambie en el lienzo
     */
    @Override
    public void layoutChildren() {
        super.layoutChildren();

        // Redimensiona el rectangulo exterior
//        outerRectangle.setWidth(getWidth());
//        outerRectangle.setHeight(getHeight());

        // Redimensiona el rectangulo interior
//        innerRectangle.setLayoutX(gap);
//        innerRectangle.setLayoutY(gap);

//        innerRectangle.setWidth(getWidth() - 2 * gap);
//        innerRectangle.setHeight(getHeight() - 2 * gap);

        // Centrar el Label en el control
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
//        requestLayout();
//        layoutChildren();
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
        Optional<String> cadena = JavaFXUtil.input("Editar", "Introduce el nuevo texto", "Actual: " + label.getText());
        cadena.ifPresent(s -> label.setText(s));
    }
}