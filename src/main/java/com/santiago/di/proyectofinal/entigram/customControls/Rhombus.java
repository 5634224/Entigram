package com.santiago.di.proyectofinal.entigram.customControls;

import com.santiago.di.proyectofinal.entigram.JavaFXUtil;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.scene.control.Control;
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
    public static final double STROKE_LINE_WIDTH = 2.0;
    private Polygon polygon;
    private LabelControl<Control> label;
    private boolean isUpdatingSize = false;

    public Rhombus(Pane contenedor) {
        super(contenedor);

        // Establece el tamaño por defecto del componente
//        setWidth(100);
//        setHeight(50);

//        // Establece el color de fondo
        this.setStyle("-fx-background-color: black");

        // Crea un rombo
        polygon = new Polygon();
        polygon.setFill(Color.WHITE); // Sin color de fondo
        polygon.setStroke(Color.BLACK); // Borde negro
        polygon.setStrokeWidth(STROKE_LINE_WIDTH);
        polygon.getPoints().setAll(50.0, 0.0,
                100.0, 50.0,
                50.0, 100.0,
                0.0, 50.0);

        // Label
        label = new LabelControl<Control>(this);
        label.get().setText("Pruebaaaaaaaaaaaaaaaaaaaaaaaaaaa\nPruebitaaaaaaaaaaaaaaaaaaaa");
        label.get().setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 14.0));
        label.get().setTextAlignment(TextAlignment.CENTER);
        label.get().setTextFill(Color.BLACK);


//        // Vincular el tamaño del rombo al tamaño del texto del Label
//        label.prefWidthProperty().addListener((obs, oldVal, newVal) -> {
//            setRhombusWidth(newVal.doubleValue() * 1.8 + 20);
////            requestLayout();
//            this.layoutChildren();
//        });
//////
//////
//        label.prefHeightProperty().addListener((obs, oldVal, newVal) -> {
//            setRhombusHeight(newVal.doubleValue() * 1.8 + 10);
////            requestLayout();
//            this.layoutChildren();
//        });

        // Añade el rombo y el label al control
        getChildren().addAll(polygon, label);
//        label.setLayoutX(25);
//        label.setLayoutY(0);
//        requestLayout();
//        layoutChildren();


//        widthProperty().addListener((obs, oldVal, newVal) -> {
//            if (!isUpdatingSize) {
//                Platform.runLater(this::updatePolygonSize);
//            }
//        });
//        heightProperty().addListener((obs, oldVal, newVal) -> {
//            if (!isUpdatingSize) {
//                Platform.runLater(this::updatePolygonSize);
//            }
//        });

        // Añade un ChangeListener a los puntos del Polygon
//        polygon.getPoints().addListener((ListChangeListener.Change<? extends Double> c) -> {
//            updateRhombusSize();
//        });

    }

    @Override
    public void layoutChildren() {
        super.layoutChildren();

        // Actualiza el tamaño del rombo
//        updateRhombusSize();
//
//        // Dibuja el rombo
//        polygon.getPoints().clear();
//        polygon.getPoints().addAll(
//                getWidth() / 2, 0.0,
//                getWidth(), getHeight() / 2,
//                getWidth() / 2, getHeight(),
//                0.0, getHeight() / 2
//        );


////        // Centra el label dentro del rombo
//        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
//        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);

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
        super.resize(width, height);

//        updatePolygonSize();

//        requestLayout();
//        layoutChildren();


        double x = (width - 100) / 2;
        double y = (height - 100) / 2;

        polygon.getPoints().setAll(
                x + 50.0, y,
                x + 100.0, y + 50.0,
                x + 50.0, y + 100.0,
                x, y + 50.0
        );
    }

//    private void updatePolygonSize() {
//        // Dibuja el rombo
////        isUpdatingSize = true;
//        polygon.getPoints().clear();
//        polygon.getPoints().addAll(
//                getWidth() / 2, 0.0,
//                getWidth(), getHeight() / 2,
//                getWidth() / 2, getHeight(),
//                0.0, getHeight() / 2
//        );
//        isUpdatingSize = false;
//    }

    private void updateRhombusSize() {
        Bounds bounds = polygon.getBoundsInLocal();
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
    }

//    public double getRhombusWidth() {
//        return getWidth();
//    }

//    public void setRhombusWidth(double width) {
////        setWidth(width);
////        requestLayout();
//    }

//    public double getRhombusHeight() {
//        return getHeight();
//    }

//    public void setRhombusHeight(double height) {
////        setHeight(height);
////        requestLayout();
//    }

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
            System.out.println("No se puede eliminar el control del contenedor");
        }
    }

    @Override
    public void editarControl(Event event) {
//        label.editarControl(event);
        Optional<String> cadena = JavaFXUtil.input("Editar", "Introduce el nuevo texto", "Actual: " + label.get().getText());
        cadena.ifPresent(s -> label.get().setText(s));
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CustomControlSkin(this);
    }
}