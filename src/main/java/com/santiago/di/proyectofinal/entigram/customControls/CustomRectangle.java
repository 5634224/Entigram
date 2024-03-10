package com.santiago.di.proyectofinal.entigram.customControls;

import com.santiago.di.proyectofinal.entigram.JavaFXUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.Optional;

public class CustomRectangle extends ArrastrableControl {
    private Label label;
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
        customRectangle.setStrokeWidth(STROKE_LINE_WIDTH);

        // Label
        label = new Label("Entidad");
        label.getStyleClass().add("texto-componentes");

        // Añade el rectangulo y el label al control
        getChildren().addAll(customRectangle, label);

        // Bindea el tamaño del label con el tamaño del rectángulo
        customRectangle.widthProperty().bind(label.widthProperty().add(20));
        customRectangle.heightProperty().bind(label.heightProperty().add(20));
    }

    @Override
    public void layoutChildren() {
        super.layoutChildren();

        // Centrar el Label en el control
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
//        customRectangle.setWidth(width);
//        customRectangle.setHeight(height);
//        requestLayout();
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
        Optional<String> cadena = JavaFXUtil.input("Editar", "Introduce el nuevo texto", "Actual: " + label.getText());
        cadena.ifPresent(s -> label.setText(s));
    }
}