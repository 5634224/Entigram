package com.santiago.di.proyectofinal.entigram.customControls;

import com.santiago.di.proyectofinal.entigram.JavaFXUtil;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Optional;

public class LabelControl<T extends Parent> extends CustomControl<T> {
    private Label label;
    public LabelControl(T panelContenedor, String texto) {
        super(panelContenedor);
        label = new Label(texto);

        // Propìedades label
        label.setFont(Font.font(14.0));
        label.setTextFill(Color.BLACK);

        // Bindea el label con el control
//        label.layoutXProperty().bind(this.layoutXProperty());
//        label.layoutYProperty().bind(this.layoutYProperty());

        label.layoutXProperty().addListener((obs, oldVal, newVal) -> {
//            this.setLayoutX(newVal.doubleValue());
//            label.setLayoutX(0);
            System.out.println("LayoutX: " + newVal.doubleValue() + " - " + this.getLayoutX());
        });
        label.layoutYProperty().addListener((obs, oldVal, newVal) -> {
//            this.setLayoutY(newVal.doubleValue());
//            label.setLayoutY(0);
            System.out.println("LayoutY: " + newVal.doubleValue() + " - " + this.getLayoutY());
        });
//        label.prefWidthProperty().bind(this.widthProperty());
//        label.prefHeightProperty().bind(this.heightProperty());

        getChildren().add(label);
        setStyle("-fx-background-color: yellow; -fx-border-color: black; -fx-border-width: 1px;");


        // Por qué no se está aplicando el setStyle? Porque el label ocupa todo el espacio del control
        // Pero se debería ver al menos el borde, no? Sí, pero no se está aplicando el estilo
        // Y cómo se aplica correctamente? Se debe aplicar el estilo al label, no al control
    }

    public LabelControl(T panelContenedor) {
        this(panelContenedor, "");
    }

    public Label get() {
        return label;
    }

    public double getLabelWidth() {
        return getWidth();
    }

    public void setLabelWidth(double width) {
        setWidth(width);
        requestLayout();
    }

    public double getLabelHeight() {
        return getHeight();
    }

    public void setLabelHeight(double height) {
        setHeight(height);
        requestLayout();
    }

    @Override
    public void eliminarControlDelContenedor(ActionEvent event) {
//        try {
//            ((Rhombus) getContenedor()).getChildren().remove(this);
//        } catch (ClassCastException e) {
//            System.out.println("No se puede eliminar el control del contenedor");
//        }
    }

    @Override
    public void editarControl(Event event) {
        Optional<String> cadena = JavaFXUtil.input("Editar", "Introduce el nuevo texto", "Actual: " + label.getText());
        cadena.ifPresent(s -> label.setText(s));
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CustomControlSkin(this);
    }
}
