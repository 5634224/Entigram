package com.santiago.di.proyectofinal.entigram.customControls;

import com.santiago.di.proyectofinal.entigram.JavaFXUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.Optional;

public class Rhombus extends ArrastrableControl {
    private Polygon polygon;
    private Label label;

    public Rhombus(Pane contenedor) {
        super(contenedor);

//        this.setStyle("-fx-background-color: yellow;");

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(50);

        // Crea un rombo
        polygon = new Polygon();
        polygon.setFill(Color.WHITE); // Sin color de fondo
        polygon.setStroke(Color.BLACK); // Borde negro
        polygon.setStrokeWidth(STROKE_LINE_WIDTH);

        polygon.getPoints().clear();
        polygon.getPoints().addAll(
                getWidth() / 2, 0.0,
                getWidth(), getHeight() / 2,
                getWidth() / 2, getHeight(),
                0.0, getHeight() / 2
        );

        // Label
        label = new Label();
//        label.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 14.0));
        label.getStyleClass().add("texto-componentes");
//        label.setTextAlignment(TextAlignment.CENTER);
        label.textProperty().addListener(this::handleTextChange);

        // Añade el rombo y el label al control
        getChildren().addAll(polygon, label);

        // Crea un ChangeListener para las propiedades widthProperty y heightProperty del Label. Cuando cambie el tamaño del label, se redimensionará el rombo
        ChangeListener<Number> labelSizeChangeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // Actualiza la anchura y altura del Polygon
                polygon.getPoints().setAll(
                        label.getWidth() / 2, 0.0,
                        label.getWidth(), label.getHeight() / 2,
                        label.getWidth() / 2, label.getHeight(),
                        0.0, label.getHeight() / 2
                );
                requestLayout();
                autosize();
            }
        };

        // Vincula las dimensiones del Polygon al Label
        label.widthProperty().addListener(labelSizeChangeListener);
        label.heightProperty().addListener(labelSizeChangeListener);

        // Escucha los cambios en la propiedad textProperty del Label para aplicar padding al label y que se expanda el rombo
        label.textProperty().addListener((observable, oldValue, newValue) -> {
            // Calcula el nuevo padding en función de la longitud del texto
            double newPaddingHorizontal = newValue.length() * 1.1; // Ajustar este factor según sea necesario
            double newPaddingVertical = newPaddingHorizontal * 1.8; // Ajusta este factor según sea necesario

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
//        setWidth(label.widthProperty().doubleValue() * 1.8 + 20);
//        setHeight(label.heightProperty().doubleValue() * 1.8 + 10);

        // Centra el label dentro del rombo
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);




//
//        System.out.println(label.getLayoutX() + " " + label.getLayoutY());
//        System.out.println(label.getWidth() + " " + label.getHeight());
//        System.out.println(label.get().getLayoutX() + " " + label.get().getLayoutY());
//        System.out.println(label.get().getWidth() + " " + label.get().getHeight());
//        requestLayout();
//        System.out.println(label.getLayoutX());
//        System.out.println(label.getLayoutY());
    }

    private void handleTextChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        // Dibuja el rombo
//        layoutChildren();


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