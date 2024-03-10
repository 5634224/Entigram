package com.santiago.di.proyectofinal.entigram.customControls;

/**
 * Interfaz que permite establecer si un control arrastrable se puede arrastrar y obtener su posición en los bordes.
 */
public interface IArrastrableControl extends IDragObservable {
    public boolean isArrastrable();
    public void setArrastrable(boolean arrastrable);
    public double[] getPosicionNorth();
    public double[] getPosicionSouth();
    public double[] getPosicionEast();
    public double[] getPosicionWest();
}
