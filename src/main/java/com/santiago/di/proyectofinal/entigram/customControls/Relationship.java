package com.santiago.di.proyectofinal.entigram.customControls;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Relationship {
    private IEntityControl entity1;
    private IArrastrableControl entity2;
    private Line line1;
    private Line line2;
    private LabelControl cardinalidad1;
    private LabelControl cardinalidad2;
    private IRelationControl rhombus;
    private Pane lienzo;
    private ChangeListener<Number> positionListener;

    /**
     * Constructor para construir una relación entre dos IEntityControl.
     * @param entity1 Control que representa la entidad 1.
     * @param entity2 Control que representa la entidad 2.
     * @param relacionador Control que representa la relación.
     * @param linea1 Línea que conecta la entidad 1 con el relacionador.
     * @param linea2 Línea que conecta el relacionador con la entidad 2.
     * @param cardinalidad1 Control que representa la cardinalidad (label) de la entidad 1.
     * @param cardinalidad2 Control que representa la cardinalidad (label) de la entidad 2.
     */
    public Relationship(IEntityControl entity1, IArrastrableControl entity2, IRelationControl relacionador, Line linea1, Line linea2, LabelControl cardinalidad1, LabelControl cardinalidad2) {
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.line1 = linea1;
        this.line2 = linea2;
        this.cardinalidad1 = cardinalidad1;
        this.cardinalidad2 = cardinalidad2;
        this.rhombus = relacionador;
        this.lienzo = entity1.getContenedor() != null ? entity1.getContenedor() : (entity2.getContenedor() != null ? entity2.getContenedor() : (relacionador.getContenedor() != null ? relacionador.getContenedor() : null));

        // Añade listeners a las propiedades de posición de las IEntityControl
        positionListener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                updateLine();
            }
        };

        if (entity1 != null) {
            ((ArrastrableControl)entity1).layoutXProperty().addListener(positionListener);
            ((ArrastrableControl)entity1).layoutYProperty().addListener(positionListener);
        }

        if (entity2 != null) {
            ((ArrastrableControl)entity2).layoutXProperty().addListener(positionListener);
            ((ArrastrableControl)entity2).layoutYProperty().addListener(positionListener);
        }

        // Coloca el rhombus entre entity1 y entity2
        if (rhombus != null) {
            double x = ((ArrastrableControl)entity1).getLayoutX() + (((ArrastrableControl)entity2).getLayoutX() - ((ArrastrableControl)entity1).getLayoutX()) / 2;
            double y = ((ArrastrableControl)entity1).getLayoutY() + (((ArrastrableControl)entity2).getLayoutY() - ((ArrastrableControl)entity1).getLayoutY()) / 2;
            ((ArrastrableControl)rhombus).setLayoutX(x);
            ((ArrastrableControl)rhombus).setLayoutY(y);
        }

        // Bindea los LabelControl. El primero se bindea con la posición inicial de la línea 1, el segundo con la posición final de la línea 2
        if (cardinalidad1 != null && cardinalidad2 != null && line2 != null && entity2 instanceof IEntityControl && rhombus != null) {
            cardinalidad1.layoutXProperty().bind(line1.startXProperty().add(line1.endXProperty()).divide(2).subtract(cardinalidad1.widthProperty().divide(2)));
            cardinalidad1.layoutYProperty().bind(line1.startYProperty().add(line1.endYProperty()).divide(2).subtract(cardinalidad1.heightProperty().divide(2)));

            cardinalidad2.layoutXProperty().bind(line2.startXProperty().add(line2.endXProperty()).divide(2).subtract(cardinalidad2.widthProperty().divide(2)));
            cardinalidad2.layoutYProperty().bind(line2.startYProperty().add(line2.endYProperty()).divide(2).subtract(cardinalidad2.heightProperty().divide(2)));
        } else {
//            if (cardinalidad1 != null) {
//                cardinalidad1.layoutXProperty().bind(line1.startXProperty().add(line1.endXProperty()).divide(2).subtract(cardinalidad1.widthProperty().divide(2)));
//                cardinalidad1.layoutYProperty().bind(line1.startYProperty().add(line1.endYProperty()).divide(2).subtract(cardinalidad1.heightProperty().divide(2)));
//            }
        }

        if (cardinalidad2 != null) {
            cardinalidad2.layoutXProperty().bind(line2.startXProperty().add(line2.endXProperty()).divide(2).subtract(cardinalidad2.widthProperty().divide(2)));
            cardinalidad2.layoutYProperty().bind(line2.startYProperty().add(line2.endYProperty()).divide(2).subtract(cardinalidad2.heightProperty().divide(2)));
        }

        // Añade la línea y el IRelationControl al lienzo
//        lienzo.getChildren().add(line1);
//        lienzo.getChildren().add(line2);
//        lienzo.getChildren().add((Pane) rhombus);

        // Actualiza la línea inicialmente
        updateLine();
    }


    /**
     * Constructor para construir una relación entre un IEntityControl y un IAtributeControl.
     * @param entity1 Control que representa la entidad.
     * @param entity2 Control que representa el atributo.
     * @param linea Línea que conecta la entidad con el atributo.
     */
    public Relationship(IEntityControl entity1, IArrastrableControl entity2, Line linea) {
        this(entity1, entity2, null, linea, null, null, null);
    }

    /**
     * Constructor que añade los controles, sin líneas ni IRelationControl.
     * @param entity1 Control que representa la entidad.
     * @param entity2 Control que representa la entidad/atributo.
     */
    public Relationship(IEntityControl entity1, IArrastrableControl entity2) {
        this(entity1, entity2, null, null, null, null, null);
    }

    /**
     * Constructor por defecto.
     */
    public Relationship() {
        this(null, null, null, null, null, null, null);
    }

    // Actualiza la posición de la(s) línea(s) para que conecte las IEntityControl
    private void updateLine() {
        if (line1 != null) {
            line1.setStartX(((ArrastrableControl)entity1).getLayoutX());
            line1.setStartY(((ArrastrableControl)entity1).getLayoutY());
            if (line2 == null || entity2 instanceof IAtributeControl || rhombus == null) {
                line1.setEndX(((ArrastrableControl)entity2).getLayoutX());
                line1.setEndY(((ArrastrableControl)entity2).getLayoutY());
            } else {
                line1.setEndX(((ArrastrableControl)rhombus).getLayoutX());
                line1.setEndY(((ArrastrableControl)rhombus).getLayoutY());

                line2.setStartX(((ArrastrableControl)rhombus).getLayoutX());
                line2.setStartY(((ArrastrableControl)rhombus).getLayoutY());
                line2.setEndX(((ArrastrableControl)entity2).getLayoutX());
                line2.setEndY(((ArrastrableControl)entity2).getLayoutY());
            }
        }
    }

    public IEntityControl getEntity1() {
        return entity1;
    }

    public void setEntity1(IEntityControl entity1) {
        if (this.entity1 != null) {
            ((ArrastrableControl)this.entity1).layoutXProperty().removeListener(positionListener);
            ((ArrastrableControl)this.entity1).layoutYProperty().removeListener(positionListener);
        }
        this.entity1 = entity1;
        ((ArrastrableControl)entity1).layoutXProperty().addListener(positionListener);
        ((ArrastrableControl)entity1).layoutYProperty().addListener(positionListener);

        // Añade la entidad al lienzo
//        lienzo.getChildren().add((ArrastrableControl) entity1);

        // Actualiza la línea
        updateLine();
    }

    public IArrastrableControl getEntity2() {
        return entity2;
    }

    public void setEntity2(IArrastrableControl entity2) {
        if (this.entity2 != null) {
            ((ArrastrableControl)this.entity2).layoutXProperty().removeListener(positionListener);
            ((ArrastrableControl)this.entity2).layoutYProperty().removeListener(positionListener);
        }
        this.entity2 = entity2;
        ((ArrastrableControl)entity2).layoutXProperty().addListener(positionListener);
        ((ArrastrableControl)entity2).layoutYProperty().addListener(positionListener);

        // Añade la entidad al lienzo
//        lienzo.getChildren().add((ArrastrableControl) entity2);

        // Actualiza la línea
        updateLine();
    }

    public Line getLine1() {
        return line1;
    }

    public void setLine1(Line line1) {
        this.line1 = line1;
    }

    public Line getLine2() {
        return line2;
    }

    public void setLine2(Line line2) {
        this.line2 = line2;
    }

    public LabelControl getCardinalidad1() {
        return cardinalidad1;
    }

    public void setCardinalidad1(LabelControl cardinalidad1) {
        this.cardinalidad1.layoutXProperty().unbind();
        this.cardinalidad1.layoutYProperty().unbind();

        this.cardinalidad1 = cardinalidad1;
        // Bindea los LabelControl. El primero se bindea con la posición inicial de la línea 1, el segundo con la posición final de la línea 2
        if (cardinalidad1 != null && line2 != null && entity2 instanceof IEntityControl && rhombus != null) {
            cardinalidad1.layoutXProperty().bind(line1.startXProperty().add(line1.endXProperty()).divide(2).subtract(cardinalidad1.widthProperty().divide(2)));
            cardinalidad1.layoutYProperty().bind(line1.startYProperty().add(line1.endYProperty()).divide(2).subtract(cardinalidad1.heightProperty().divide(2)));
        }
    }

    public LabelControl getCardinalidad2() {
        return cardinalidad2;
    }

    public void setCardinalidad2(LabelControl cardinalidad2) {
        this.cardinalidad2 = cardinalidad2;

        // Bindea los LabelControl. El primero se bindea con la posición inicial de la línea 1, el segundo con la posición final de la línea 2
        if (cardinalidad2 != null && line2 != null && entity2 instanceof IEntityControl && rhombus != null) {
            cardinalidad2.layoutXProperty().bind(line2.startXProperty().add(line2.endXProperty()).divide(2).subtract(cardinalidad2.widthProperty().divide(2)));
            cardinalidad2.layoutYProperty().bind(line2.startYProperty().add(line2.endYProperty()).divide(2).subtract(cardinalidad2.heightProperty().divide(2)));
        }
    }

    public IRelationControl getRhombus() {
        return rhombus;
    }

    public void setRhombus(IRelationControl rhombus) {
        this.rhombus = rhombus;
        // Coloca el rhombus entre entity1 y entity2
        if (rhombus != null) {
            double x = ((ArrastrableControl)entity1).getLayoutX() + (((ArrastrableControl)entity2).getLayoutX() - ((ArrastrableControl)entity1).getLayoutX()) / 2;
            double y = ((ArrastrableControl)entity1).getLayoutY() + (((ArrastrableControl)entity2).getLayoutY() - ((ArrastrableControl)entity1).getLayoutY()) / 2;
            ((ArrastrableControl)rhombus).setLayoutX(x);
            ((ArrastrableControl)rhombus).setLayoutY(y);
        }

        // Actualiza la línea
        updateLine();
    }

    public Pane getLienzo() {
        return lienzo;
    }

    public void setLienzo(Pane lienzo) {
        this.lienzo = lienzo;
    }
}