package com.example.taskexecutor.builder;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PaneBuilder {

    public static Pane mouseClickPane(TextField xField, TextField yField, TextField waitField) {
        VBox content = new VBox(10);

        Label xLabel = new Label("X");
        Label yLabel = new Label("Y");
        Label waitLabel = new Label("Wait");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.add(xLabel, 0, 0);
        grid.add(xField, 1, 0);
        GridPane.setHgrow(xField, Priority.ALWAYS);

        grid.add(yLabel, 0, 1);
        grid.add(yField, 1, 1);
        GridPane.setHgrow(yField, Priority.ALWAYS);

        grid.add(waitLabel, 0, 2);
        grid.add(waitField, 1, 2);
        GridPane.setHgrow(waitField, Priority.ALWAYS);

        content.getChildren().add(grid);

        return content;
    }

    public static Pane keyPressPane(TextField keyField, TextField waitField) {
        VBox content = new VBox(10);

        Label keyLabel = new Label("Key");
        Label waitLabel = new Label("Wait");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        // TODO switch to dropdown or record pressed key
        grid.add(keyLabel, 0, 0);
        grid.add(keyField, 1, 0);
        GridPane.setHgrow(keyField, Priority.ALWAYS);

        grid.add(waitLabel, 0, 1);
        grid.add(waitField, 1, 1);
        GridPane.setHgrow(waitField, Priority.ALWAYS);

        content.getChildren().add(grid);

        return content;
    }
}
