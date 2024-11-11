module com.example.taskexecutor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.taskexecutor to javafx.fxml;
    exports com.example.taskexecutor;
}