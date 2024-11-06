module com.example.moveme {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.compiler;


    opens com.example.moveme to javafx.fxml;
    exports com.example.moveme;
}