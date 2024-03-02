package com.santiago.di.proyectofinal.entigram.customControls;

public interface DragObservable {
    void addObserver(DragObserver observer);
    void removeObserver(DragObserver observer);
}
