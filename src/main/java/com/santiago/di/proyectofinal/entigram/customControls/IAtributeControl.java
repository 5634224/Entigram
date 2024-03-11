package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * Interfaz que permite identificar a los controles que representan atributos.
 * Hereda los métodos de las interfaces IERControl e IArrastrableControl.
 */
public interface IAtributeControl extends IERControl, IArrastrableControl {
    /**
     * Método que permite obtener si el atributo es una clave primaria.
     * @param clavePrimaria Valor que indica si el atributo es una clave primaria.
     */
    void setClavePrimaria(boolean clavePrimaria);

    /**
     * Método que permite obtener si el atributo es una clave primaria.
     * @return Valor que indica si el atributo es una clave primaria.
     */
    boolean isClavePrimaria();
}
