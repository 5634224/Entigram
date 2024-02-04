package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class CustomRectangle extends Control {
    private List<DragObserver> observers;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private Label label;
    private Rectangle customRectangle;


    public CustomRectangle() {
        super();

        // Inicializa la lista de observadores
        observers = new ArrayList<>();

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(75);

        // Crea un rectangulo
        customRectangle = new Rectangle();

        // Sin color de fondo
        customRectangle.setFill(Color.WHITE);

        // Borde negro
        customRectangle.setStroke(Color.BLACK);

        // Label
        label = new Label("Prueba");
        label.setFont(Font.font(14.0));
        label.setTextFill(Color.BLACK);

        // Agregar controladores de eventos de arrastre
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseReleased);

        // Añade el rectangulo y el label al control
        getChildren().addAll(customRectangle, label);
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
        customRectangle.setWidth(getWidth());
        customRectangle.setHeight(getHeight());

        label.applyCss();
        label.layout();

        // Centrar el Label en el control
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
//        customRectangle.setWidth(width);
//        customRectangle.setHeight(height);
        requestLayout();
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
        return new SkinBase<CustomRectangle>(this) {
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