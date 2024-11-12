package com.example.taskexecutor;

import com.example.taskexecutor.builder.ViewBuilder;
import com.example.taskexecutor.constant.AppConstants;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        ViewBuilder viewBuilder = new ViewBuilder();
        Scene scene = viewBuilder.build();
        stage.setTitle(AppConstants.TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}