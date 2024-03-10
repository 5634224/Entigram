package com.santiago.di.proyectofinal.entigram.customControls;

import com.santiago.di.proyectofinal.entigram.JavaFXUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

import java.util.Optional;

public class DoubleRhombus extends ArrastrableControl implements IRelationControl {
    private Polygon outerPolygon;
    private Polygon innerPolygon;
    private Label label;

    public DoubleRhombus(Pane contenedor) {
        super(contenedor);

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(50);

        // Crea el rombo interior
        innerPolygon = new Polygon();
        innerPolygon.setFill(Color.WHITE); // Sin color de fondo
        innerPolygon.setStroke(Color.BLACK); // Borde negro
        innerPolygon.setStrokeWidth(STROKE_LINE_WIDTH);
        innerPolygon.getPoints().clear();
        innerPolygon.getPoints().addAll(
                getWidth() / 2, Math.max(getGapHeight(), getGapWidth()),
                getWidth() - Math.min(getGapWidth(), getGapHeight()), getHeight() / 2,
                getWidth() / 2, getHeight() - Math.max(getGapHeight(), getGapWidth()),
                Math.min(getGapWidth(), getGapHeight()), getHeight() / 2
        );

        // Crea el rombo exterior
        outerPolygon = new Polygon();
        outerPolygon.setFill(Color.WHITE); // Sin color de fondo
        outerPolygon.setStroke(Color.BLACK); // Borde negro
        outerPolygon.setStrokeWidth(STROKE_LINE_WIDTH);
        outerPolygon.getPoints().clear();
        outerPolygon.getPoints().addAll(
                getWidth() / 2, 0.0,
                getWidth(), getHeight() / 2,
                getWidth() / 2, getHeight(),
                0.0, getHeight() / 2
        );

        // Label
        label = new Label();
        label.getStyleClass().add("texto-componentes");

        // Añade el rectangulo y el label al control
        getChildren().addAll(outerPolygon, innerPolygon, label);

        // Crea un ChangeListener para las propiedades widthProperty y heightProperty del Label. Cuando cambie el tamaño del label, se redimensionará el rombo
        ChangeListener<Number> labelSizeChangeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // Actualiza la anchura y altura del Polygon
                innerPolygon.getPoints().setAll(
                        label.getWidth() / 2, Math.min(getGapHeight(), getGapWidth()),
                        label.getWidth() - Math.max(getGapWidth(), getGapHeight()), label.getHeight() / 2,
                        label.getWidth() / 2, label.getHeight() - Math.min(getGapHeight(), getGapWidth()),
                        Math.max(getGapWidth(), getGapHeight()), label.getHeight() / 2
                );
                outerPolygon.getPoints().setAll(
                        label.getWidth() / 2, 0.0,
                        label.getWidth(), label.getHeight() / 2,
                        label.getWidth() / 2, label.getHeight(),
                        0.0, label.getHeight() / 2
                );

                requestLayout();
//                layoutChildren();
                autosize();
            }
        };

        // Vincula las dimensiones del Polygon al Label
        label.widthProperty().addListener(labelSizeChangeListener);
        label.heightProperty().addListener(labelSizeChangeListener);

        // Escucha los cambios en la propiedad textProperty del Label para aplicar padding al label y que se expanda el rombo
        label.textProperty().addListener((observable, oldValue, newValue) -> {

            // Calcula el nuevo padding en función de la longitud del texto
            double newPaddingHorizontal = newValue.length() * 1.3; // Ajustar este factor según sea necesario
            double newPaddingVertical = newPaddingHorizontal * 2.1; // Ajusta este factor según sea necesario

            // Asegurar que el padding no sea menor que un valor mínimo
            newPaddingHorizontal = Math.max(newPaddingHorizontal, 30); // Ajustar este valor mínimo según sea necesario
            newPaddingVertical = Math.max(newPaddingVertical, 15); // Ajustar este valor mínimo según sea necesario

            // Aplica el nuevo padding al Label
            label.setPadding(new Insets(newPaddingVertical, newPaddingHorizontal, newPaddingVertical, newPaddingHorizontal));

            // Solicita un nuevo layout para aplicar los cambios
            requestLayout();
//            layoutChildren();
        });

        // Asigna el texto por defecto
        label.setText("Relación");
    }

    @Override
    public void layoutChildren() {
        super.layoutChildren();
//        // Dibuja el rombo exterior
//        outerPolygon.getPoints().clear();
//        outerPolygon.getPoints().addAll(
//            0.0, getHeight() / 2,
//            getWidth() / 2, 0.0,
//            getWidth(), getHeight() / 2,
//            getWidth() / 2, getHeight()
//        );
//
//        // Dibuja el rombo interior
//        final double GAP_WIDTH = getWidth() / 12.5;
//        final double GAP_HEIGHT = getHeight() / 12.5;
//        innerPolygon.getPoints().clear();
//        innerPolygon.getPoints().addAll(
//                GAP_WIDTH, getHeight() / 2,
//                getWidth() / 2, GAP_HEIGHT,
//                getWidth() - GAP_WIDTH, getHeight() / 2,
//                getWidth() / 2, getHeight() - GAP_HEIGHT
//      );

        // Centra el label dentro del rombo
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

//    @Override
//    public void resize(double width, double height) {
//        super.resize(width, height);
//        // Actualiza el tamaño del rombo
////        requestLayout();
//    }

    private double getGapWidth() {
        return getWidth() / 10.5;
    }

    private double getGapHeight() {
        return getHeight() / 10.5;
    }

//    public double getRhombusWidth() {
//        return getWidth();
//    }
//
//    public void setRhombusWidth(double width) {
//        setWidth(width);
//        requestLayout();
//    }
//
//    public double getRhombusHeight() {
//        return getHeight();
//    }
//
//    public void setRhombusHeight(double height) {
//        setHeight(height);
//        requestLayout();
//    }

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