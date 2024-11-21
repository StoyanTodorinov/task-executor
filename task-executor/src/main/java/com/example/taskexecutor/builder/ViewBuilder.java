package com.example.taskexecutor.builder;

import com.example.taskexecutor.constant.Constants;
import com.example.taskexecutor.controller.ClickRecordController;
import com.example.taskexecutor.enums.ActionType;
import com.example.taskexecutor.misc.Action;
import com.example.taskexecutor.misc.GlobalKeyListener;
import com.github.kwhat.jnativehook.GlobalScreen;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.awt.*;

public class ViewBuilder {

    // TODO normal naming, make private and use getters and setters
    public boolean RECORD_NEXT_KEY_PRESS = false;
    private final ClickRecordController clickRecordController = new ClickRecordController();

    public Scene build() {
        TableView<Action> tableView = createTableView();
        GridPane buttonPane = createButtonPanel(tableView);

        VBox vbox = new VBox(10, buttonPane, tableView);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        VBox.setVgrow(buttonPane, Priority.ALWAYS);

        setStartStopListener(vbox, tableView);
        setupKeyPressRecorded(vbox, tableView);
        registerGlobalKeyOperations(tableView);

        return new Scene(vbox, Constants.WIDTH, Constants.HEIGHT);
    }

    private TableView<Action> createTableView() {
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

        TableColumn<Action, Double> waitColumn = new TableColumn<>("WAIT");
        waitColumn.setCellValueFactory(new PropertyValueFactory<>("delay"));
        waitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        waitColumn.setOnEditCommit(event -> event.getRowValue().setDelay(event.getNewValue()));

        TableColumn<Action, String> actionTypeColumn = new TableColumn<>("TYPE");
        actionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        actionTypeColumn.setEditable(false);

        TableColumn<Action, Button> removeActionColumn = new TableColumn<>("ACTION");
        removeActionColumn.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));
        removeActionColumn.setEditable(false);

        // Add columns into TableView
        table.getColumns().addAll(xColumn, yColumn, keyColumn, waitColumn, actionTypeColumn, removeActionColumn);

        xColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        yColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        keyColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        waitColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        actionTypeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        removeActionColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

//        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }

    private GridPane createButtonPanel(TableView<Action> tableView) {
        GridPane buttonPanel = new GridPane();

        Button addLeftMouseClick = createLeftMouseClickButton(tableView);
        Button addRightMouseClick = createRightMouseClickButton(tableView);
        Button addKeyboardPress = createKeyboardPressButton(tableView);

        buttonPanel.add(addLeftMouseClick, 1, 0);
        buttonPanel.add(addRightMouseClick, 2, 0);
        buttonPanel.add(addKeyboardPress, 3, 0);

        buttonPanel.setAlignment(Pos.CENTER);

        return buttonPanel;
    }

    private Button createLeftMouseClickButton(final TableView<Action> tableView) {
        Button addMouseClick = new Button(Constants.ADD_LEFT_MOUSE_CLICK);
        addMouseClick.setOnAction(_ -> {
            // TODO
            try {
                System.out.println("ADDING LEFT MOUSE CLICK RECORD...");
                Thread.sleep(2000);
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                Action mouseAction = new Action(mouseLocation.x, mouseLocation.y, ActionType.LEFT_MOUSE_CLICK,
                        this.clickRecordController, tableView);
                this.clickRecordController.addAction(mouseAction);
                tableView.getItems().add(mouseAction);
            } catch (InterruptedException e) {
                System.out.println("FAILED ADDING MOUSE CLICK RECORD...");
                System.out.println(e.getMessage());
            }
        });

        return addMouseClick;
    }

    private Button createRightMouseClickButton(final TableView<Action> tableView) {
        Button addMouseClick = new Button(Constants.ADD_RIGHT_MOUSE_CLICK);
        addMouseClick.setOnAction(_ -> {
            // TODO
            try {
                System.out.println("ADDING RIGHT MOUSE CLICK RECORD...");
                Thread.sleep(2000);
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                Action mouseAction = new Action(mouseLocation.x, mouseLocation.y, ActionType.RIGHT_MOUSE_CLICK,
                        this.clickRecordController, tableView);
                this.clickRecordController.addAction(mouseAction);
                tableView.getItems().add(mouseAction);
            } catch (InterruptedException e) {
                System.out.println("FAILED ADDING MOUSE CLICK RECORD...");
                System.out.println(e.getMessage());
            }
        });

        return addMouseClick;
    }

    private Button createKeyboardPressButton(final TableView<Action> tableView) {

        Button keyboardPressButton = new Button(Constants.ADD_KEYBOARD_PRESS);
        keyboardPressButton.setOnAction(_ -> {
            tableView.requestFocus();
            this.RECORD_NEXT_KEY_PRESS = true;
        });

        return keyboardPressButton;
    }

    private void setupKeyPressRecorded(VBox root, final TableView<Action> tableView) {
        root.setOnKeyReleased(event -> {
            KeyCode keyPressed = event.getCode();
            if (this.RECORD_NEXT_KEY_PRESS
                    && (!keyPressed.getName().equalsIgnoreCase(Constants.F_11)
                    && (!keyPressed.getName().equalsIgnoreCase(Constants.F_12)))) {
                System.out.println("KEY PRESSED: '" + keyPressed.getName() + "'!");
                Action keyboardAction = new Action(keyPressed.getName(), keyPressed.getCode(),
                        this.clickRecordController, tableView);
                this.clickRecordController.addAction(keyboardAction);
                tableView.getItems().add(keyboardAction);

                this.RECORD_NEXT_KEY_PRESS = false;
            }
        });
    }

    private void setStartStopListener(VBox root, final TableView<Action> tableView) {
        root.setOnKeyPressed(event -> {
            KeyCode keyPressed = event.getCode();
            if (keyPressed.getName().equalsIgnoreCase(Constants.F_11)) {
                try {
                    this.clickRecordController.start(tableView);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (keyPressed.getName().equalsIgnoreCase(Constants.F_12)) {
                this.clickRecordController.stop();
            }
        });
    }

    private void registerGlobalKeyOperations(final TableView<Action> tableView) {
        try {
            // Register the global key listener
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(new GlobalKeyListener(this.clickRecordController, tableView));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                GlobalScreen.unregisterNativeHook();
                System.out.println("Global hook unregistered. Application is exiting.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }
}
