package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class CustomEllipse extends Control {
    private List<DragObserver> observers;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private Ellipse ellipse;
    private Label label;

    public CustomEllipse() {
        super();

        // Inicializa la lista de observadores
        observers = new ArrayList<>();

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(75);

        // Crea una elipse
        ellipse = new Ellipse();

        // Sin color de fondo
        ellipse.setFill(Color.WHITE);

        // Borde negro
        ellipse.setStroke(Color.BLACK);

        // Label
        label = new Label("Prueba");
        label.setFont(Font.font(14.0));
        label.setTextFill(Color.BLACK);

        // Agregar controladores de eventos de arrastre
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseReleased);

        // Añade los controles al lienzo
        getChildren().addAll(ellipse, label);
    }

    public void addObserver(DragObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(DragObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String mensaje) {
        for (DragObserver observer : observers) {
            observer.updateEstado(mensaje);
        }
    }

    @Override
    public void layoutChildren() {
        ellipse.setCenterX(getWidth() / 2);
        ellipse.setCenterY(getHeight() / 2);
        ellipse.setRadiusX(getWidth() / 2);
        ellipse.setRadiusY(getHeight() / 2);

        // Centrar el Label en el control
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
        requestLayout();
    }

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
        return label.getText();
    }

    public void setLabelText(String text) {
        label.setText(text);
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
    protected Skin<?> createDefaultSkin() {
        return new SkinBase<CustomEllipse>(this) {
            @Override
            protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
                // Layout logic goes here
            }

            @Override
            public void dispose() {
                // Cleanup logic goes here
            }
        };
    }

    private void handleMousePressed(MouseEvent e) {
        orgSceneX = e.getSceneX();
        orgSceneY = e.getSceneY();
        orgTranslateX = this.getTranslateX();
        orgTranslateY = this.getTranslateY();

        notifyObservers("Seleccionado");
    }

    private void handleMouseDragged(MouseEvent e) {
        double offsetX = e.getSceneX() - orgSceneX;
        double offsetY = e.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;

        this.setTranslateX(newTranslateX);
        this.setTranslateY(newTranslateY);

        notifyObservers("Arrastrando...");
    }

    private void handleMouseReleased(MouseEvent e) {
        notifyObservers("Arrastre finalizado");
    }
}