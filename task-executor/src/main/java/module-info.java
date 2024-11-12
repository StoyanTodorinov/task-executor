module com.example.taskexecutor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.taskexecutor to javafx.fxml;
    exports com.example.taskexecutor;
    exports com.example.taskexecutor.controller;
    opens com.example.taskexecutor.controller to javafx.fxml;
}