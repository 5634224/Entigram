module com.santiago.di.proyectofinal.entigram {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.santiago.di.proyectofinal.entigram to javafx.fxml;
    exports com.santiago.di.proyectofinal.entigram;
    exports com.santiago.di.proyectofinal.entigram.controller;
    opens com.santiago.di.proyectofinal.entigram.controller to javafx.fxml;
}