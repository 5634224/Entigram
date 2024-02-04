package com.santiago.di.proyectofinal.entigram.controller;

import com.santiago.di.proyectofinal.entigram.Escenario;
import com.santiago.di.proyectofinal.entigram.customControls.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class EntigramController implements Initializable, DragObserver {
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
    @FXML
    private Button btnDeshacer;
    @FXML
    private Button btnRehacer;

    /*==================== Métodos ====================*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        CustomRectangle rectangulo = new CustomRectangle();
//        rectangulo.setRectangleWidth(100.0);
//        rectangulo.setRectangleHeight(75.0);
//        rectangulo.setLayoutX(50);
//        rectangulo.setLayoutY(50);
//        rectangulo.setLabelText("Entidad");
//
//        pnLienzo.getChildren().add(rectangulo);
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

    @FXML
    public void btnLinea_onAction(ActionEvent actionEvent) {
        lblEstado.setText("Dibujando línea");

    }

    private void onDragDetected(Event event, String tipo) {
        Button miBoton = (Button) event.getSource();
        Dragboard db = miBoton.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(tipo);
        db.setContent(content);

        event.consume();
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
    public void btnAtributoMultivaluado_onDragDetected(Event event) {
        lblEstado.setText("Arrastrando atributo multivaluado");
        onDragDetected(event, "AtributoMultivaluado");
    }

    @FXML
    public void btnAtributoDerivado_onDragDetected(Event event) {
        lblEstado.setText("Arrastrando atributo derivado");
        onDragDetected(event, "AtributoDerivado");
    }

    private void pnLienzo_Entidad_onDragDropped(DragEvent event, Pane miPanel) {
        CustomRectangle rectangulo = new CustomRectangle();
        rectangulo.setRectangleWidth(100.0);
        rectangulo.setRectangleHeight(100.0);
        rectangulo.setLayoutX(event != null ? event.getX() : 50.0);
        rectangulo.setLayoutY(event != null ? event.getY() : 50.0);

        miPanel.getChildren().add(rectangulo);

        rectangulo.addObserver(this);
        lblEstado.setText("Entidad fuerte insertada");
    }

    private void pnLienzo_RelacionDebil_onDragDropped(DragEvent event, Pane miPanel) {
        DoubleRhombus drombo = new DoubleRhombus();
        drombo.setRhombusWidth(100.0);
        drombo.setRhombusHeight(50.0);
        drombo.setLayoutX(event != null ? event.getX() : 50.0);
        drombo.setLayoutY(event != null ? event.getY() : 50.0);

        miPanel.getChildren().add(drombo);

        drombo.addObserver(this);
        lblEstado.setText("Relación débil insertada");
    }

    private void pnLienzo_Relacion_onDragDropped(DragEvent event, Pane miPanel) {
        Rhombus rombo = new Rhombus();
        rombo.setRhombusWidth(100.0);
        rombo.setRhombusHeight(50.0);
        rombo.setLayoutX(event != null ? event.getX() : 50.0);
        rombo.setLayoutY(event != null ? event.getY() : 50.0);

        miPanel.getChildren().add(rombo);

        rombo.addObserver(this);
        lblEstado.setText("Relación fuerte insertada");
    }

    private void pnLienzo_Atributo_onDragDropped(DragEvent event, Pane miPanel) {
        CustomEllipse elipse = new CustomEllipse();
        elipse.setEllipseWidth(100.0);
        elipse.setEllipseHeight(50.0);
        elipse.setLayoutX(event != null ? event.getX() : 50.0);
        elipse.setLayoutY(event != null ? event.getY() : 50.0);

        miPanel.getChildren().add(elipse);

        elipse.addObserver(this);
        lblEstado.setText("Atributo insertado");
    }

    private void pnLienzo_EntidadDebil_onDragDropped(DragEvent event, Pane miPanel) {
        DoubleRectangle drectangulo = new DoubleRectangle();
        drectangulo.setRectangleWidth(100.0);
        drectangulo.setRectangleHeight(100.0);
        drectangulo.setLayoutX(event != null ? event.getX() : 50.0);
        drectangulo.setLayoutY(event != null ? event.getY() : 50.0);

        miPanel.getChildren().add(drectangulo);

        drectangulo.addObserver(this);
        lblEstado.setText("Entidad débil insertada");
    }

    private void pnLienzo_AtributoMultivaluado_onDragDropped(DragEvent event, Pane miPanel) {
        DoubleEllipse elipse = new DoubleEllipse();
        elipse.setEllipseWidth(100.0);
        elipse.setEllipseHeight(50.0);
        elipse.setLayoutX(event != null ? event.getX() : 50.0);
        elipse.setLayoutY(event != null ? event.getY() : 50.0);

        miPanel.getChildren().add(elipse);

        elipse.addObserver(this);
        lblEstado.setText("Atributo multivaluado insertado");
    }
    private void pnLienzo_AtributoDerivado_onDragDropped(DragEvent event, Pane miPanel) {
        CustomEllipse elipse = new CustomEllipse();
        elipse.setEllipseWidth(100.0);
        elipse.setEllipseHeight(50.0);
        elipse.setDashedBorder(true);

        elipse.setLayoutX(event != null ? event.getX() : 50.0);
        elipse.setLayoutY(event != null ? event.getY() : 50.0);

        miPanel.getChildren().add(elipse);

        elipse.addObserver(this);
        lblEstado.setText("Atributo derivado insertado");
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
        Dragboard db = event.getDragboard(); // Obtiene el contenido del dragboard (portapapeles de arrastre)
        boolean success = false;
        if (db.hasString()) {
            String tipo = db.getString();
            switch (tipo) {
                case "Entidad":
                    pnLienzo_Entidad_onDragDropped(event, miPanel);
                    success = true;
                    break;
                case "EntidadDebil":
                    pnLienzo_EntidadDebil_onDragDropped(event, miPanel);
                    success = true;
                    break;
                case "Atributo":
                    pnLienzo_Atributo_onDragDropped(event, miPanel);
                    success = true;
                    break;
                case "Relacion":
                    pnLienzo_Relacion_onDragDropped(event, miPanel);
                    success = true;
                    break;
                case "RelacionDebil":
                    pnLienzo_RelacionDebil_onDragDropped(event, miPanel);
                    success = true;
                    break;
                case "AtributoMultivaluado":
                    pnLienzo_AtributoMultivaluado_onDragDropped(event, miPanel);
                    success = true;
                    break;
                case "AtributoDerivado":
                    pnLienzo_AtributoDerivado_onDragDropped(event, miPanel);
                    success = true;
                    break;
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    @FXML
    public void btnDeshacer(ActionEvent actionEvent) {
        lblEstado.setText("Deshacer");
    }

    @FXML
    public void btnRehacer(ActionEvent actionEvent) {
        lblEstado.setText("Rehacer");
    }

    @FXML
    public void btnEntidad_onAction(ActionEvent actionEvent) {
        pnLienzo_Entidad_onDragDropped(null, pnLienzo);
    }

    @FXML
    public void btnAtributo_onAction(ActionEvent actionEvent) {
        pnLienzo_Atributo_onDragDropped(null, pnLienzo);
    }

    @FXML
    public void btnEntidadDebil_onAction(ActionEvent actionEvent) {
        pnLienzo_EntidadDebil_onDragDropped(null, pnLienzo);
    }

    @FXML
    public void btnAtributoDerivado_onAction(ActionEvent actionEvent) {
        pnLienzo_AtributoDerivado_onDragDropped(null, pnLienzo);
    }

    @FXML
    public void btnAtributoMultivaluado_onAction(ActionEvent actionEvent) {
        pnLienzo_AtributoMultivaluado_onDragDropped(null, pnLienzo);
    }

    @FXML
    public void btnRelacion_onAction(ActionEvent actionEvent) {
        pnLienzo_Relacion_onDragDropped(null, pnLienzo);
    }

    @FXML
    public void btnRelacionDebil_onAction(ActionEvent actionEvent) {
        pnLienzo_RelacionDebil_onDragDropped(null, pnLienzo);
    }

    @Override
    public void updateEstado(String estado) {
        lblEstado.setText(estado);
    }
}