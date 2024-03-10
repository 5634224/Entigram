package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.scene.Parent;

/**
 * Interfaz que permite identificar a los controles personalizados, y expone los métodos de CustomControl.
 * @param <T> Tipo de contenedor que se utilizará para el control.
 */
public interface ICustomControl<T extends Parent> {
    void setContenedor(T contenedor);

    T getContenedor();

    void enableEditarControl(boolean enable);
}
