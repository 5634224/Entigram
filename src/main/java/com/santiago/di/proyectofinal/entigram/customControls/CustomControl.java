package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class CustomControl<T extends Parent> extends Control {
    private ContextMenu rightClickMenu;
    private MenuItem eliminar;
    private MenuItem editar;
    private T contenedor;
    public CustomControl(T contenedor) {
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
        if (event.isSecondaryButtonDown()) {
            System.out.println("Hizo clic derecho");
            rightClickMenu.show(this, event.getScreenX(), event.getScreenY());
            // Por qué no se está mostrando el rightClickMenu? Porque el control no tiene un contenedor
            // Cómo se soluciona? Se debe establecer el contenedor del control
            // Y cómo se hace eso? Se debe crear un método setContenedor en la clase CustomControl
            // Y cómo se llama ese método? setContenedor
            // Y qué debo hacer dentro del método setContenedor? Asignar el contenedor al atributo contenedor
            // Y cómo se hace eso? this.contenedor = contenedor;
            // Pero eso ya lo hago en el constructor, no? Sí, pero el contenedor puede cambiar

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
