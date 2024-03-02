package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public abstract class ArrastrableControl extends CustomControl<Pane> implements DragObservable {
    private boolean arrastrable;
    private List<DragObserver> observers;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    public ArrastrableControl(Pane contenedor) {
        super(contenedor); // Llama al constructor de la clase padre

        // Inicializa el control como arrastrable
        arrastrable = true;

        // Inicializa la lista de observadores
        observers = new ArrayList<>();

        // Agregar controladores de eventos de arrastre
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseReleased);
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

    public boolean isArrastrable() {
        return arrastrable;
    }

    public void setArrastrable(boolean arrastrable) {
        this.arrastrable = arrastrable;
    }

    private void handleMousePressed(MouseEvent e) {
        if (arrastrable) {
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = this.getTranslateX();
            orgTranslateY = this.getTranslateY();
        }

        notifyObservers("Seleccionado");
    }

    private void handleMouseDragged(MouseEvent e) {
        if (arrastrable) {
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            this.setTranslateX(newTranslateX);
            this.setTranslateY(newTranslateY);
            notifyObservers("Arrastrando...");
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        notifyObservers("Seleccionado");
    }
    public double[] getPosicionNorth() {
        double posiciones[] = new double[2];
        posiciones[0] = this.getLayoutX() + this.getWidth() / 2;
        posiciones[1] = this.getLayoutY();
        return posiciones;
    }

    public double[] getPositionSouth() {
        double posiciones[] = new double[2];
        posiciones[0] = this.getLayoutX() + this.getWidth() / 2;
        posiciones[1] = this.getLayoutY() + this.getHeight();
        return posiciones;
    }

    public double[] getPositionEast() {
        double posiciones[] = new double[2];
        posiciones[0] = this.getLayoutX() + this.getWidth();
        posiciones[1] = this.getLayoutY() + this.getHeight() / 2;
        return posiciones;
    }

    public double[] getPositionWest() {
        double posiciones[] = new double[2];
        posiciones[0] = this.getLayoutX();
        posiciones[1] = this.getLayoutY() + this.getHeight() / 2;
        return posiciones;
    }
}