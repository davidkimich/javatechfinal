module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.javafxapp to javafx.fxml;
    exports com.example.javafxapp;
}