package com.santiago.di.proyectofinal.entigram.controller;

import com.santiago.di.proyectofinal.entigram.Escenario;
import com.santiago.di.proyectofinal.entigram.customControls.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class EntigramController implements Initializable {
    /*==================== Atributos ====================*/
    private Escenario escenario;

    @FXML
    private Button btnEntidad;
    @FXML
    private Button btnAtributo;
    @FXML
    private Button btnLinea;
    @FXML
    private Button btnRelacion;
    @FXML
    private Button btnEntidadDebil;
    @FXML
    private Button btnAtributoMultivaluado;
    @FXML
    private Button btnAtributoDerivado;
    @FXML
    private Pane pnLienzo;
    @FXML
    private TextField txtFieldNombre;
    @FXML
    private TextArea txtAreaDescripcion;
    @FXML
    private Label lblEstado;
    @FXML
    private Button btnRelacionDebil;

    /*==================== Métodos ====================*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomRectangle rectangulo = new CustomRectangle();
        rectangulo.setRectangleWidth(100.0);
        rectangulo.setRectangleHeight(75.0);
        rectangulo.setLayoutX(50);
        rectangulo.setLayoutY(50);
        rectangulo.setLabelText("Entidad");

        pnLienzo.getChildren().add(rectangulo);
    }

    /**
     * Método que establece el escenario actual
     */
    public void setStage(Escenario escenario) {
        this.escenario = escenario;
    }

//    public void prueba() {
//        Node[] prueba = new Control[10];
//        prueba[0] = new Rectangle(100.0, 100.0);
//        prueba[1] = new Label("Hola");
//        prueba[2] = btnLinea;
//    }

    private void onDragDetected(Event event, String tipo) {
        Button miBoton = (Button) event.getSource();
        Dragboard db = miBoton.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(tipo);
        db.setContent(content);

        event.consume();
    }

    @FXML
    public void btnLinea_onAction(ActionEvent actionEvent) {
        lblEstado.setText("Dibujando línea");

    }

    @FXML
    public void btnEntidad_onDragDetected(Event event) {
        lblEstado.setText("Arrastrando entidad");
        onDragDetected(event, "Entidad");
    }

    @FXML
    public void btnAtributo_onDragDetected(Event event) {
        lblEstado.setText("Arrastrando atributo");
        onDragDetected(event, "Atributo");
    }

    @FXML
    public void btnRelacion_onDragDetected(Event event) {
        lblEstado.setText("Arrastrando relación");
        onDragDetected(event, "Relacion");
    }

    @FXML
    public void btnEntidadDebil_onDragDetected(Event event) {
        lblEstado.setText("Arrastrando entidad débil");
        onDragDetected(event, "EntidadDebil");
    }

    @FXML
    public void btnRelacionDebil_onDragDetected(Event event) {
        lblEstado.setText("Arrastrando relación débil");
        onDragDetected(event, "RelacionDebil");
    }

    @FXML
    public void pnLienzo_onDragOver(DragEvent event) {
        Pane miPanel = (Pane) event.getSource();
        if (event.getGestureSource() != miPanel && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    @FXML
    public void pnLienzo_onDragDropped(DragEvent event) {
        Pane miPanel = (Pane) event.getSource();
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            String tipo = db.getString();
            switch (tipo) {
                case "Entidad":
                    CustomRectangle rectangulo = new CustomRectangle();
                    rectangulo.setRectangleWidth(100.0);
                    rectangulo.setRectangleHeight(100.0);
                    rectangulo.setLayoutX(event.getX());
                    rectangulo.setLayoutY(event.getY());

                    miPanel.getChildren().add(rectangulo);
                    success = true;
                    break;
                case "EntidadDebil":
                    DoubleRectangle drectangulo = new DoubleRectangle();
                    drectangulo.setRectangleWidth(100.0);
                    drectangulo.setRectangleHeight(100.0);
                    drectangulo.setLayoutX(event.getX());
                    drectangulo.setLayoutY(event.getY());

                    miPanel.getChildren().add(drectangulo);
                    success = true;
                    break;
                case "Atributo":
                    CustomEllipse elipse = new CustomEllipse();
                    elipse.setEllipseWidth(100.0);
                    elipse.setEllipseHeight(50.0);
                    elipse.setLayoutX(event.getX());
                    elipse.setLayoutY(event.getY());

                    miPanel.getChildren().add(elipse);
                    success = true;
                    break;
                case "Relacion":
                    Rhombus rombo = new Rhombus();
                    rombo.setRhombusWidth(100.0);
                    rombo.setRhombusHeight(50.0);
                    rombo.setLayoutX(event.getX());
                    rombo.setLayoutY(event.getY());

                    miPanel.getChildren().add(rombo);
                    success = true;
                    break;
                case "RelacionDebil":
                    DoubleRhombus drombo = new DoubleRhombus();
                    drombo.setRhombusWidth(100.0);
                    drombo.setRhombusHeight(50.0);
                    drombo.setLayoutX(event.getX());
                    drombo.setLayoutY(event.getY());

                    miPanel.getChildren().add(drombo);
                    success = true;
                    break;
                case "AtributoMultivaluado":
                    break;
                case "AtributoDerivado":
                    break;
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }
}