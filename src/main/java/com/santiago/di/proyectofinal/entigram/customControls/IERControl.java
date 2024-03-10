package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.event.ActionEvent;
import javafx.event.Event;

/**
 * Interfaz que define el comportamiento de un control de Entidad/Relacion cuyo texto (label) puede ser modificado.

 */
public interface IERControl {
    /**
     * Establece el texto del control
     * @param text
     */
    void setLabelText(String text);

    /**
     * Obtiene el texto del control
     * @return texto del control
     */
    String getLabelText();

    /**
     * Edita el contenido (label) del control.
     * Este método debe usarse para controlar la edición del texto del control (mediante doble clic o click derecho > Editar).
     * @param event
     */
    void editarControl(Event event);

    /**
     * Elimina el control del contenedor.
     * Este método debe usarse para controlar la eliminación del control (mediante click derecho > Eliminar).
     * @param event
     */
    void eliminarControlDelContenedor(ActionEvent event);
}
