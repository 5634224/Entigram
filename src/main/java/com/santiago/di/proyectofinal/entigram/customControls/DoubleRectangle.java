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

public class DoubleRectangle extends Control {
    private List<DragObserver> observers;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private Rectangle outerRectangle;
    private Rectangle innerRectangle;
    private Label label;

    public DoubleRectangle() {
        super();

        // Inicializa la lista de observadores
        observers = new ArrayList<>();

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(75);

        // Crea un doble rectangulo
        outerRectangle = new Rectangle();
        innerRectangle = new Rectangle();

        // Sin color de fondo
        outerRectangle.setFill(Color.WHITE);
        innerRectangle.setFill(Color.WHITE);

        // Borde negro
        outerRectangle.setStroke(Color.BLACK);
        innerRectangle.setStroke(Color.BLACK);

        // Label
        label = new Label("Prueba");
        label.setFont(Font.font(14.0));
        label.setTextFill(Color.BLACK);

        // Agregar controladores de eventos de arrastre
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseReleased);

        // Añade el rectangulo y el label al control
        getChildren().addAll(outerRectangle, innerRectangle, label);
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
        double gap = 3.5; // Define un gap de la decima parte del total del ancho

        // Redimensiona el rectangulo exterior
        outerRectangle.setWidth(getWidth());
        outerRectangle.setHeight(getHeight());

        // Redimensiona el rectangulo interior
        innerRectangle.setLayoutX(gap);
        innerRectangle.setLayoutY(gap);
        innerRectangle.setWidth(getWidth() - 2 * gap);
        innerRectangle.setHeight(getHeight() - 2 * gap);

        // Centrar el Label en el control
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
        requestLayout();
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
        return new SkinBase<DoubleRectangle>(this) {
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