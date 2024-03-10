package com.santiago.di.proyectofinal.entigram.customControls;

public interface IDragObservable {
    void addObserver(IDragObserver observer);
    void removeObserver(IDragObserver observer);
}
