package com.santiago.di.proyectofinal.entigram.customControls;

import com.santiago.di.proyectofinal.entigram.JavaFXUtil;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Optional;

public class Rhombus extends ArrastrableControl {
    public static final double STROKE_LINE_WIDTH = 2.0;
    private Polygon polygon;
    private Label label;

    public Rhombus(Pane contenedor) {
        super(contenedor);

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(50);

        // Crea un rombo
        polygon = new Polygon();
        polygon.setFill(Color.WHITE); // Sin color de fondo
        polygon.setStroke(Color.BLACK); // Borde negro
        polygon.setStrokeWidth(STROKE_LINE_WIDTH);

        // Label
        label = new Label();
        label.setText("Pruebaaaaaaaaaaaaaaaaaaaaaaaaaaa\nPruebitaaaaaaaaaaaaaaaaaaaa");
        label.setFont(Font.font(14.0));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setTextFill(Color.BLACK);

        // Vincular el tamaño del rombo al tamaño del texto del Label
        label.widthProperty().addListener((obs, oldVal, newVal) -> {
            setRhombusWidth(newVal.doubleValue() * 1.8 + 20);
//            requestLayout();
            layoutChildren();
        });


        label.heightProperty().addListener((obs, oldVal, newVal) -> {
            setRhombusHeight(newVal.doubleValue() * 1.8 + 10);
//            requestLayout();
            layoutChildren();
        });

        // Añade el rombo y el label al control
        getChildren().addAll(polygon, label);
//        label.setLayoutX(25);
//        label.setLayoutY(0);
        requestLayout();
        layoutChildren();
    }

    @Override
    public void layoutChildren() {
        super.layoutChildren();
//        // Dibuja el rombo
        polygon.getPoints().clear();
        polygon.getPoints().addAll(
            getWidth() / 2, 0.0,
            getWidth(), getHeight() / 2,
            getWidth() / 2, getHeight(),
            0.0, getHeight() / 2
        );
//        // Centra el label dentro del rombo
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
//        System.out.println(label.getLayoutX() + " " + label.getLayoutY());
//        System.out.println(label.getWidth() + " " + label.getHeight());
//        System.out.println(label.get().getLayoutX() + " " + label.get().getLayoutY());
//        System.out.println(label.get().getWidth() + " " + label.get().getHeight());
//        requestLayout();
//        System.out.println(label.getLayoutX());
//        System.out.println(label.getLayoutY());
    }

    @Override
    public void resize(double width, double height) {
        // Actualiza el tamaño del rombo
//        super.resize(width, height);
//        requestLayout();
        layoutChildren();
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
    public void eliminarControlDelContenedor(ActionEvent event) {
        try {
            ((Pane) getContenedor()).getChildren().remove(this);
        } catch (ClassCastException e) {
            System.out.println("No se puede eliminar el control del contenedor");
        }
    }

    @Override
    public void editarControl(Event event) {
//        label.editarControl(event);
        Optional<String> cadena = JavaFXUtil.input("Editar", "Introduce el nuevo texto", "Actual: " + label.getText());
        cadena.ifPresent(s -> label.setText(s));
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CustomControlSkin(this);
    }
}