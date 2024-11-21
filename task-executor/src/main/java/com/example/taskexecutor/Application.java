package com.example.taskexecutor;

import com.example.taskexecutor.builder.ViewBuilder;
import com.example.taskexecutor.constant.Constants;
import com.example.taskexecutor.misc.GlobalKeyListener;
import com.github.kwhat.jnativehook.GlobalScreen;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        ViewBuilder viewBuilder = new ViewBuilder();
        Scene scene = viewBuilder.build();
        stage.setTitle(Constants.TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}