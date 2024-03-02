package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class CustomEllipse extends ArrastrableControl {
    private Ellipse ellipse;
    private LabelControl label;

    public CustomEllipse(Pane contenedor) {
        super(contenedor);

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(75);

        // Crea una elipse
        ellipse = new Ellipse();
        ellipse.setFill(Color.WHITE); // Sin color de fondo
        ellipse.setStroke(Color.BLACK); // Borde negro

        // Label
        label = new LabelControl(contenedor, "Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\nAdiooooooooooooooooooooooooos\nOtra cosa mássssssssss\nJajajaaaaaaaaaaaaaaaaaaaaa");
        label.get().setFont(Font.font(14.0));
        label.get().setTextAlignment(TextAlignment.CENTER);
        label.get().setTextFill(Color.BLACK);
//        label.setLayoutX(0);
//        label.setLayoutY(0);

        // Vincular el tamaño de la elipse al tamaño del texto del Label
        ellipse.radiusXProperty().bind(label.widthProperty().divide(1.5).add(20)); // Ajusta según el ancho del texto del Label
        ellipse.radiusYProperty().bind(label.heightProperty().divide(1.5).add(10)); // Ajusta según la altura del texto del Label

        // Posicionar el Label en el centro de la elipse
//        label.layoutXProperty().bind(ellipse.centerXProperty().subtract(label.widthProperty().divide(2)));
//        label.layoutYProperty().bind(ellipse.centerYProperty().subtract(label.heightProperty().divide(2)));

        // Añade los controles al lienzo
        getChildren().addAll(ellipse, label);
    }

//    @Override
//    public void layoutChildren() {
//        super.layoutChildren();
//        ellipse.setCenterX(getWidth() / 2);
//        ellipse.setCenterY(getHeight() / 2);
//        ellipse.setRadiusX(getWidth() / 2);
//        ellipse.setRadiusY(getHeight() / 2);
////
////        // Centrar el Label en el control
////        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
////        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
//    }
//
//    @Override
//    public void resize(double width, double height) {
////        super.resize(width, height);
////        requestLayout();
//        layoutChildren();
//    }

    public double getEllipseWidth() {
        return ellipse.getRadiusX() * 2;
    }

    public void setEllipseWidth(double width) {
        setWidth(width);
        requestLayout();
    }

    public double getEllipseHeight() {
        return ellipse.getRadiusY() * 2;
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

    public void setDashedBorder(boolean dashed) {
        ObservableList<Double> strokeDashArray = ellipse.getStrokeDashArray();
        strokeDashArray.clear();
        if (dashed) {
            strokeDashArray.addAll(10.0, 10.0); // Este patrón crea una línea discontinua con segmentos de 10px y espacios de 10px
        }
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

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CustomControlSkin(this);
    }

}