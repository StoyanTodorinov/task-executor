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

import java.util.Objects;

public class Application extends javafx.application.Application {

    // TODO fix action execution stop bug - OK
    // TODO disable all buttons on start and enable them on stop - OK
    // TODO add first column to indicate the order of operation: 1, 2, 3 etc. -
    // TODO remove -1 and n/a values -
    // TODO implement option to perform left click on cursor location started with F10 - 
    // TODO implement an option to run for an amount of time
    // TODO implement an option to run for a given number or rotations
    // TODO (optional) add keyboard combination for adding left, right and keyboard clicks

    @Override
    public void start(Stage stage) {
        ViewBuilder viewBuilder = new ViewBuilder();
        Scene scene = viewBuilder.build();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setTitle(Constants.TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}