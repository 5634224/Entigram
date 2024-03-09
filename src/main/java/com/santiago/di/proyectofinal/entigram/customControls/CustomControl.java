package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class CustomControl<T extends Parent> extends Control {
    private ContextMenu rightClickMenu;
    private MenuItem eliminar;
    private MenuItem editar;
    private T contenedor;
    public CustomControl(T contenedor) {
        // Configura el control
//        this.size

        // Inicializa los elementos del menú de clic derecho
        this.contenedor = contenedor;
        rightClickMenu = new ContextMenu();
        eliminar = new MenuItem("Eliminar");
        editar = new MenuItem("Editar");

        // Definimos el menú de clic derecho sobre el control
        rightClickMenu.getItems().addAll(editar, eliminar);

        // Mostrar el ContextMenu cuando se hace clic con el botón derecho del ratón sobre el control
        this.setOnMouseClicked(this::onMouseClicked);

        // Controlamos las acciones de los elementos del menú
        editar.setOnAction(this::editarControl);
        eliminar.setOnAction(this::eliminarControlDelContenedor);
    }

    private void onMouseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            System.out.println("Hizo clic derecho");
            rightClickMenu.show(this, event.getScreenX(), event.getScreenY());
        } else if (event.getClickCount() == 2) {
            editarControl(event);
        }
    }

    public void setContenedor(T contenedor) {
        this.contenedor = contenedor;
    }

    public T getContenedor() {
        return contenedor;
    }

    public void enableEditarControl(boolean enable) {
        editar.setDisable(!enable);
    }
    public abstract void eliminarControlDelContenedor(ActionEvent event);

    public abstract void editarControl(Event event); // ActionEvent si es evento del menu contextual, MouseEvent si es doble clic
    @Override
    protected Skin<?> createDefaultSkin() {
        return new CustomControlSkin(this);
    }

}
