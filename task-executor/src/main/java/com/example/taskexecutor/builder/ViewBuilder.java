package com.example.taskexecutor.builder;

import com.example.taskexecutor.constant.AppConstants;
import com.example.taskexecutor.controller.ClickRecordController;
import com.example.taskexecutor.dialog.KeyboardPressDialog;
import com.example.taskexecutor.dialog.MouseClickDialog;
import com.example.taskexecutor.misc.Action;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

public class ViewBuilder {

    private final ClickRecordController clickRecordController = new ClickRecordController();

    public Scene build() {
        TableView<Action> tableView = createTableView();

        Button buttonMouse = new Button();
        buttonMouse.setText(AppConstants.START);
        buttonMouse.setOnAction(_ -> {
            // TODO
            System.out.println("STARTING...");
        });

        Button buttonKey = new Button();
        buttonKey.setText(AppConstants.STOP);
        buttonKey.setOnAction(_ -> {
            // TODO
            System.out.println("STOPPING...");
        });

        VBox pane = new VBox(10);
        pane.getChildren().addAll(buttonMouse, buttonKey, tableView);

        Group root = new Group(pane);

        return new Scene(root, AppConstants.WIDTH, AppConstants.HEIGHT);
    }

    public TableView<Action> createTableView() {
        // New Table View
        TableView<Action> table = new TableView<>();
        table.setEditable(true);

        // Create columns
        TableColumn<Action, Integer> xColumn = new TableColumn<>("X");
        xColumn.setCellValueFactory(new PropertyValueFactory<>("screen_x"));
        xColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        xColumn.setOnEditCommit(event -> event.getRowValue().setScreen_x(event.getNewValue()));

        TableColumn<Action, Integer> yColumn = new TableColumn<>("Y");
        yColumn.setCellValueFactory(new PropertyValueFactory<>("screen_y"));
        yColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        yColumn.setOnEditCommit(event -> event.getRowValue().setScreen_y(event.getNewValue()));

        TableColumn<Action, String> keyColumn = new TableColumn<>("KEY");
        keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        keyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        keyColumn.setOnEditCommit(event -> event.getRowValue().setKey(event.getNewValue()));

        TableColumn<Action, Integer> waitColumn = new TableColumn<>("WAIT");
        waitColumn.setCellValueFactory(new PropertyValueFactory<>("delay"));
        waitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        waitColumn.setOnEditCommit(event -> event.getRowValue().setDelay(event.getNewValue()));

        // Add columns into TableView
        table.getColumns().add(xColumn);
        table.getColumns().add(yColumn);
        table.getColumns().add(keyColumn);
        table.getColumns().add(waitColumn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Load objects into table
        table.getItems().add(new Action());
        table.getItems().add(new Action());
        table.getItems().add(new Action());
        table.getItems().add(new Action());
        table.getItems().add(new Action());

        return table;
    }
}
