package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class DoubleRhombus extends Control {
    private List<DragObserver> observers;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private Polygon outerPolygon;
    private Polygon innerPolygon;
    private Label label;

    public DoubleRhombus() {
        super();

        // Inicializa la lista de observadores
        observers = new ArrayList<>();

        // Establece el tamaño por defecto del componente
        setWidth(100);
        setHeight(50);

        // Crea un rombo doble
        outerPolygon = new Polygon();
        innerPolygon = new Polygon();

        // Sin color de fondo
        outerPolygon.setFill(Color.WHITE);
        innerPolygon.setFill(Color.WHITE);

        // Borde negro
        outerPolygon.setStroke(Color.BLACK);
        innerPolygon.setStroke(Color.BLACK);

        // Label
        label = new Label("Prueba");
        label.setFont(Font.font(14.0));
        label.setTextFill(Color.BLACK);

        // Agregar controladores de eventos de arrastre
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseReleased);

        // Añade el rectangulo y el label al control
        getChildren().addAll(outerPolygon, innerPolygon, label);
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
        // Dibuja el rombo exterior
        outerPolygon.getPoints().clear();
        outerPolygon.getPoints().addAll(
            0.0, getHeight() / 2,
            getWidth() / 2, 0.0,
            getWidth(), getHeight() / 2,
            getWidth() / 2, getHeight()
        );

        // Dibuja el rombo interior
        final double GAP_WIDTH = getWidth() / 12.5;
        final double GAP_HEIGHT = getHeight() / 12.5;
        innerPolygon.getPoints().clear();
        innerPolygon.getPoints().addAll(
                GAP_WIDTH, getHeight() / 2,
                getWidth() / 2, GAP_HEIGHT,
                getWidth() - GAP_WIDTH, getHeight() / 2,
                getWidth() / 2, getHeight() - GAP_HEIGHT
        );

        // Centra el label dentro del rombo
        label.setLayoutX(getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutY(getHeight() / 2 - label.getHeight() / 2);
    }

    @Override
    public void resize(double width, double height) {
        // Actualiza el tamaño del rombo
        requestLayout();
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
    protected Skin<?> createDefaultSkin() {
        return new SkinBase<DoubleRhombus>(this) {
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