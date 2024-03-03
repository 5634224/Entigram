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
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class EntigramController implements Initializable, DragObserver {
    public static final double duracionToolTips = 0.1;
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
    @FXML
    private Tooltip toolTipDeshacer;
    @FXML
    private Tooltip toolTipRelacion;
    @FXML
    private Tooltip toolTipAtributoMultivaluado;
    @FXML
    private Tooltip toolTipAtributo;
    @FXML
    private Tooltip toolTipEntidadDebil;
    @FXML
    private Tooltip toolTipLineaRelacion;
    @FXML
    private Tooltip toolTipEntidad;
    @FXML
    private Tooltip toolTipAtributoDerivado;
    @FXML
    private Tooltip toolTipRelacionDebil;
    @FXML
    private Tooltip toolTipRehacer;

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
        prueba();

        // Establece el tiempo de espera para mostrar los tooltips
        toolTipDeshacer.setShowDelay(Duration.seconds(duracionToolTips));
        toolTipRelacion.setShowDelay(Duration.seconds(duracionToolTips));
        toolTipAtributoMultivaluado.setShowDelay(Duration.seconds(duracionToolTips));
        toolTipAtributo.setShowDelay(Duration.seconds(duracionToolTips));
        toolTipEntidadDebil.setShowDelay(Duration.seconds(duracionToolTips));
        toolTipLineaRelacion.setShowDelay(Duration.seconds(duracionToolTips));
        toolTipEntidad.setShowDelay(Duration.seconds(duracionToolTips));
        toolTipAtributoDerivado.setShowDelay(Duration.seconds(duracionToolTips));
        toolTipRelacionDebil.setShowDelay(Duration.seconds(duracionToolTips));
        toolTipRehacer.setShowDelay(Duration.seconds(duracionToolTips));
    }

    /**
     * Método que establece el escenario actual
     */
    public void setStage(Escenario escenario) {
        this.escenario = escenario;
    }

    public void prueba() {
        class pruebita extends Control {
            Label lbl;
            public pruebita() {
                setWidth(100);
                setHeight(50);
                lbl = new Label("Hola");
                getChildren().addAll(lbl);
            }

//            @Override
//            public void resize(double width, double height) {
//                super.resize(width, height);
//            }

            @Override
            protected Skin<?> createDefaultSkin() {
                return new CustomControlSkin(this);
            }
        }

        pruebita pr = new pruebita();
        pnLienzo.getChildren().addAll(pr);
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
        CustomRectangle rectangulo = new CustomRectangle(miPanel);
        rectangulo.setRectangleWidth(100.0);
        rectangulo.setRectangleHeight(100.0);
        rectangulo.setLayoutX(event != null ? event.getX() : 50.0);
        rectangulo.setLayoutY(event != null ? event.getY() : 50.0);

        miPanel.getChildren().add(rectangulo);

        rectangulo.addObserver(this);
        lblEstado.setText("Entidad fuerte insertada");
    }

    private void pnLienzo_RelacionDebil_onDragDropped(DragEvent event, Pane miPanel) {
        // Crea un nuevo rombo
        DoubleRhombus drombo = new DoubleRhombus(miPanel);
        drombo.setRhombusWidth(100.0);
        drombo.setRhombusHeight(50.0);
        drombo.setLayoutX(event != null ? event.getX() : 50.0);
        drombo.setLayoutY(event != null ? event.getY() : 50.0);

        // Añade el rombo al panel y añade el controlador como observador
        miPanel.getChildren().add(drombo);

        drombo.addObserver(this);
        lblEstado.setText("Relación débil insertada");
    }

    private void pnLienzo_Relacion_onDragDropped(DragEvent event, Pane miPanel) {
        // Crea un nuevo rombo
        Rhombus rombo = new Rhombus(miPanel);
        rombo.setRhombusWidth(100.0);
        rombo.setRhombusHeight(50.0);
        rombo.setLayoutX(event != null ? event.getX() : 50.0);
        rombo.setLayoutY(event != null ? event.getY() : 50.0);

        // Añade el rombo al panel y añade el controlador como observador
        miPanel.getChildren().add(rombo);
        rombo.addObserver(this);

        // Actualiza el estado
        lblEstado.setText("Relación fuerte insertada");
    }

    private void pnLienzo_Atributo_onDragDropped(DragEvent event, Pane miPanel) {
        CustomEllipse elipse = new CustomEllipse(miPanel);
//        elipse.setEllipseWidth(100.0);
//        elipse.setEllipseHeight(50.0);
        elipse.setLayoutX(event != null ? event.getX() : 50.0);
        elipse.setLayoutY(event != null ? event.getY() : 50.0);

        miPanel.getChildren().add(elipse);

        elipse.addObserver(this);
        lblEstado.setText("Atributo insertado");
    }

    private void pnLienzo_EntidadDebil_onDragDropped(DragEvent event, Pane miPanel) {
        DoubleRectangle drectangulo = new DoubleRectangle(miPanel);
        drectangulo.setRectangleWidth(100.0);
        drectangulo.setRectangleHeight(100.0);
        drectangulo.setLayoutX(event != null ? event.getX() : 50.0);
        drectangulo.setLayoutY(event != null ? event.getY() : 50.0);

        miPanel.getChildren().add(drectangulo);

        drectangulo.addObserver(this);
        lblEstado.setText("Entidad débil insertada");
    }

    private void pnLienzo_AtributoMultivaluado_onDragDropped(DragEvent event, Pane miPanel) {
        DoubleEllipse elipse = new DoubleEllipse(miPanel);
        elipse.setEllipseWidth(100.0);
        elipse.setEllipseHeight(50.0);
        elipse.setLayoutX(event != null ? event.getX() : 50.0);
        elipse.setLayoutY(event != null ? event.getY() : 50.0);

        miPanel.getChildren().add(elipse);

        elipse.addObserver(this);
        lblEstado.setText("Atributo multivaluado insertado");
    }
    private void pnLienzo_AtributoDerivado_onDragDropped(DragEvent event, Pane miPanel) {
        CustomEllipse elipse = new CustomEllipse(miPanel);
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
//        System.out.println(miPanel == pnLienzo);
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
    public void btnLinea_onAction(ActionEvent actionEvent) {
        LabelControl label = new LabelControl(pnLienzo, "Línea");
        label.setLabelWidth(200.0);
        label.setLabelHeight(50.0);
//        label.get().setText("Línea");

        label.setLayoutX(50.0);
        label.setLayoutY(50.0);

        pnLienzo.getChildren().add(label);

//        label.addObserver(this);

        lblEstado.setText("Dibujando línea");
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