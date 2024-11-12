package com.example.taskexecutor.builder;

import com.example.taskexecutor.constant.AppConstants;
import com.example.taskexecutor.controller.ClickRecordController;
import com.example.taskexecutor.dialog.KeyboardPressDialog;
import com.example.taskexecutor.dialog.MouseClickDialog;
import com.example.taskexecutor.misc.Action;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ViewBuilder {

    private final ClickRecordController clickRecordController = new ClickRecordController();

    public Scene build() {
        Button buttonMouse = new Button();
        buttonMouse.setText(AppConstants.ADD_MOUSE_LEFT_CLICK_EVENT_BUTTON);
        buttonMouse.setOnAction(_ -> {
            Dialog<Action> dialog = new MouseClickDialog();
            dialog.showAndWait().ifPresent(action -> {
                System.out.println(action.screen_x + " : " + action.screen_y + " : " + action.delay);
                clickRecordController.addAction(action);
            });
        });

        Button buttonKey = new Button();
        buttonKey.setText(AppConstants.ADD_KEYBOARD_EVENT_BUTTON);
        buttonKey.setOnAction(_ -> {
            Dialog<Action> dialog = new KeyboardPressDialog();
            dialog.showAndWait().ifPresent(action -> {
                System.out.println(action.key + " : " + action.delay);
                clickRecordController.addAction(action);
            });
        });

        VBox pane = new VBox(10);
        pane.getChildren().addAll(buttonMouse, buttonKey, createTableView());

        Group root = new Group(pane);

        return new Scene(root, AppConstants.WIDTH, AppConstants.HEIGHT);
    }

    public VBox createTableView() {
        // New Table View
        TableView tbv = new TableView();
        // Create two columns
        TableColumn<String, Action> cl1 = new TableColumn<>("TYPE");
        cl1.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Integer, Action> cl2 = new TableColumn<>("OPERATION");
        cl2.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Integer, Action> cl3 = new TableColumn<>("DELAY");
        cl3.setCellValueFactory(new PropertyValueFactory<>("delay"));

        // Add two columns into TableView
        tbv.getColumns().add(cl1);
        tbv.getColumns().add(cl2);
        tbv.getColumns().add(cl3);

        // Load objects into table
        for (Action action : clickRecordController.getActions()) {
            tbv.getItems().add(action);
        }

        VBox vbox = new VBox();
        vbox.getChildren().addAll(tbv);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }
}
