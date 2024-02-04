package com.santiago.di.proyectofinal.entigram;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

/**
 * Clase que implementa funcionalidad adicional respecto a los escenarios.
 * Entre otras funcionalidades, permite volver a la escena anterior.
 * @author Santiago Francisco San Pablo Raposo
 * @version 28.11.2023
 */
public class Escenario {

    /*===================== CAMPOS =====================*/
    /**
     * Variable estática que contiene el escenario de forma pública para el controlador de cualquier escena (ya que están en el mismo paquete)
     */
    private Stage escenario;

    /**
     * Pila de escenas para poder volver a la escena anterior
     */
    private Stack<Scene> escenas = new Stack<>();

    /*==================== MÉTODOS ====================*/

    /**
     * Constructor de la clase
     * @param escenario Escenario que se quiere establecer como escenario actual
     */
    public Escenario(Stage escenario) {
        this.escenario = escenario;
    }

    /**
     * Método que establece la escena actual
     */
    protected void setEscena(Scene escena) {
        this.escenario.setScene(escena);
        this.escenas.push(escena);
    }

    /**
     * Método que obtiene la escena actual
     */
    public Scene getEscena() {
        return this.escenario.getScene();
    }

    /**
     * Método que permite volver a la escena anterior
     */
    protected void getSceneBack() {
        if (!this.escenas.empty()) {
            this.escenas.pop();
            this.escenario.setScene(escenas.peek());
        }
    }
}
