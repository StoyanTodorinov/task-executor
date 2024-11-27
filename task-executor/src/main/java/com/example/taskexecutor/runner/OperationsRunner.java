package com.example.taskexecutor.runner;

import com.example.taskexecutor.controller.ClickRecordController;
import com.example.taskexecutor.enums.AppState;
import com.example.taskexecutor.misc.Action;
import javafx.application.Platform;
import javafx.scene.control.TableView;

public class OperationsRunner implements Runnable {

    private final ClickRecordController clickRecordController;
    private final TableView<Action> tableView;

    public OperationsRunner(ClickRecordController clickRecordController, TableView<Action> tableView) {
        this.clickRecordController = clickRecordController;
        this.tableView = tableView;
    }

    @Override
    public void run() {
        Platform.runLater(tableView::requestFocus); // focus table to allow better highlight of the executed action

        while (true) {
            for (Action action : this.clickRecordController.getActions()) {
                System.out.println("ACTION: " + action.toString());
                action.execute();
                highlightCurrentActionRow(action);
                try {
                    System.out.println((long) (action.delay * 1000));
                    Thread.sleep((long) (action.delay * 1000)); // action.delay is in seconds, make in into milliseconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                if (this.clickRecordController.getState().get() != AppState.RUNNING) {
                    return;
                }
            }
        }
    }

    private void highlightCurrentActionRow(Action action) {
        Platform.runLater(() -> {
            int actionIndex = tableView.getItems().indexOf(action);
            tableView.getSelectionModel().select(actionIndex);
        });
    }
}
