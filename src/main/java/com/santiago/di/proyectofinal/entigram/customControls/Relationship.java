package com.santiago.di.proyectofinal.entigram.customControls;

import com.santiago.di.proyectofinal.entigram.model.Entity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Relationship {
    private IEntityControl entity1;
    private IArrastrableControl entity2;
    private Line line;
    private IRelationControl rhombus;
    private Pane lienzo;

    public Relationship(IEntityControl entity1, IArrastrableControl entity2, IRelationControl relacionador, Line linea) {
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.line = linea;
        this.rhombus = relacionador;
        this.lienzo = entity1.getContenedor() != null ? entity1.getContenedor() : entity2.getContenedor() != null ? entity2.getContenedor() : relacionador.getContenedor() != null ? relacionador.getContenedor() : null;

        // Añade listeners a las propiedades de posición de las IEntityControl
        ChangeListener<Number> positionListener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                updateLine();
            }
        };
        ((ArrastrableControl)entity1).layoutXProperty().addListener(positionListener);
        ((ArrastrableControl)entity1).layoutYProperty().addListener(positionListener);
        ((ArrastrableControl)entity2).layoutXProperty().addListener(positionListener);
        ((ArrastrableControl)entity2).layoutYProperty().addListener(positionListener);

        // Añade la línea y el IRelationControl al lienzo
        lienzo.getChildren().add(line);
        lienzo.getChildren().add((Pane) rhombus);
    }

    public Relationship(IEntityControl entity1, IArrastrableControl entity2, Line linea) {
        this(entity1, entity2, null, linea);
    }

    public Relationship(IEntityControl entity1, IArrastrableControl entity2) {
        this(entity1, entity2, null, null);
    }

    public Relationship() {
        this(null, null, null, null);
    }

    // Actualiza la posición de la línea para que conecte las IEntityControl
    private void updateLine() {
        line.setStartX(((ArrastrableControl)entity1).getLayoutX());
        line.setStartY(((ArrastrableControl)entity1).getLayoutY());
        line.setEndX(((ArrastrableControl)entity2).getLayoutX());
        line.setEndY(((ArrastrableControl)entity2).getLayoutY());
    }

    public IEntityControl getEntity1() {
        return entity1;
    }

    public void setEntity1(IEntityControl entity1) {
        this.entity1 = entity1;
    }

    public IArrastrableControl getEntity2() {
        return entity2;
    }

    public void setEntity2(IArrastrableControl entity2) {
        this.entity2 = entity2;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public IRelationControl getRhombus() {
        return rhombus;
    }

    public void setRhombus(IRelationControl rhombus) {
        this.rhombus = rhombus;
    }

    public Pane getLienzo() {
        return lienzo;
    }

    public void setLienzo(Pane lienzo) {
        this.lienzo = lienzo;
    }
}