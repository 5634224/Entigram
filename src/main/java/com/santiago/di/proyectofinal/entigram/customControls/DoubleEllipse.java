package com.santiago.di.proyectofinal.entigram.customControls;

import com.santiago.di.proyectofinal.entigram.JavaFXUtil;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;

import java.util.Optional;

public class DoubleEllipse extends ArrastrableControl implements IAtributeControl {
    private Ellipse outerEllipse;
    private Ellipse innerEllipse;
    private Label label;

    public DoubleEllipse(Pane contenedor) {
        super(contenedor);

        // Añade una opción al menú contextual para establecer si es clave primaria
        MenuItem clavePrimariaMenuItem = new MenuItem("Clave primaria");
        clavePrimariaMenuItem.setOnAction(this::toggleClavePrimaria);
        SeparatorMenuItem separador = new SeparatorMenuItem();
        getRightClickMenu().getItems().addAll(separador, clavePrimariaMenuItem);

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(75);

        // Crea la elipse interior
        innerEllipse = new Ellipse();
        innerEllipse.setFill(Color.WHITE); // Sin color de fondo
        innerEllipse.setStroke(Color.BLACK); // Borde negro
        innerEllipse.setStrokeWidth(STROKE_LINE_WIDTH);

        // Crea la elipse exterior
        outerEllipse = new Ellipse();
        outerEllipse.setFill(Color.WHITE); // Sin color de fondo
        outerEllipse.setStroke(Color.BLACK); // Borde negro
        outerEllipse.setStrokeWidth(STROKE_LINE_WIDTH);

        // Label
        label = new Label("Atributo multivaluado");
        label.getStyleClass().add("texto-componentes");

        // Vincular el tamaño de la elipse al tamaño del texto del Label
        innerEllipse.radiusXProperty().bind(label.widthProperty().divide(1.5).add(20)); // Ajusta según el ancho del texto del Label
        innerEllipse.radiusYProperty().bind(label.heightProperty().divide(1.5).add(10)); // Ajusta según la altura del texto del Label
        outerEllipse.radiusXProperty().bind(innerEllipse.radiusXProperty().multiply(1.1));
        outerEllipse.radiusYProperty().bind(innerEllipse.radiusYProperty().multiply(1.2));

        getChildren().addAll(outerEllipse, innerEllipse, label);
    }

    private void toggleClavePrimaria(ActionEvent actionEvent) {
        setClavePrimaria(!isClavePrimaria());
    }

//    @Override
//    public void layoutChildren() {
//        super.layoutChildren();
//        // Redimensiona la elipse exterior
////        outerEllipse.setCenterX(getWidth() / 2);
////        outerEllipse.setCenterY(getHeight() / 2);
////        outerEllipse.setRadiusX(getWidth() / 2);
////        outerEllipse.setRadiusY(getHeight() / 2);
//
//        // Redimensiona la elipse interior
//        final double INNER_ELLIPSE_RATIO = 0.9; // Ajusta este valor para cambiar el tamaño de la elipse interior
////        innerEllipse.setCenterX(getWidth() / 2);
////        innerEllipse.setCenterY(getHeight() / 2);
////        innerEllipse.setRadiusX(outerEllipse.getRadiusX() * INNER_ELLIPSE_RATIO);
////        innerEllipse.setRadiusY(outerEllipse.getRadiusY() * INNER_ELLIPSE_RATIO);
//
//        // Centrar el Label en el control
//        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
//        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
//    }

//    @Override
//    public void resize(double width, double height) {
//        super.resize(width, height);
//    }

//    public double getEllipseWidth() {
//        return outerEllipse.getRadiusX() * 2;
//    }
//
//    public void setEllipseWidth(double width) {
//        setWidth(width);
//        requestLayout();
//    }
//
//    public double getEllipseHeight() {
//        return outerEllipse.getRadiusY() * 2;
//    }
//
//    public void setEllipseHeight(double height) {
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

    @Override
    public void setClavePrimaria(boolean clavePrimaria) {
        // Pone el label en subrayado si es clave primaria
        label.setUnderline(clavePrimaria);
    }

    @Override
    public boolean isClavePrimaria() {
        return label.isUnderline();
    }
}