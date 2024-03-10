package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.scene.Parent;
import javafx.scene.layout.Pane; /**
 * Interfaz que permite establecer si un control arrastrable se puede arrastrar y obtener su posición en los bordes.
 */
public interface IArrastrableControl extends ICustomControl<Pane>, IDragObservable {
    public boolean isArrastrable();
    public void setArrastrable(boolean arrastrable);
    public double[] getPosicionNorth();
    public double[] getPosicionSouth();
    public double[] getPosicionEast();
    public double[] getPosicionWest();
}
