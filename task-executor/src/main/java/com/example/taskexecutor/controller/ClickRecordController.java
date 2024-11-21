package com.example.taskexecutor.controller;

import com.example.taskexecutor.enums.AppState;
import com.example.taskexecutor.misc.Action;
import com.example.taskexecutor.runner.OperationsRunner;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class ClickRecordController {

    private AppState state = AppState.IDLE;
    private final List<Action> actions = new ArrayList<>();

    public List<Action> getActions() {
        return new ArrayList<>(actions);
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public void removeAction(Action action) {
        this.actions.remove(action);
    }

    public void start(TableView<Action> tableView) throws InterruptedException {
        // TODO optional - make all buttons except STOP disabled
        if (state == AppState.RUNNING) {
            throw new RuntimeException("CANNOT START ACTION EXECUTION MULTIPLE TIMES!");
        }

        if (this.actions.isEmpty()) {
            throw new RuntimeException("START FAILED, NO ACTIONS!");
        }
        setState(AppState.RUNNING);
        OperationsRunner runner = new OperationsRunner(this, tableView);
        Thread thread = new Thread(runner);
        thread.start();
    }

    public void stop() {
        if (state == AppState.STOPPED) {
            throw new RuntimeException("ACTION EXECUTION IS ALREADY STOPPED!");
        }
        System.out.println("PROGRAM STOP");
        setState(AppState.STOPPED);
        // TODO optional - enable all buttons
    }

    public AppState getState() {
        return this.state;
    }

    public void setState(AppState newState) {
        this.state = newState;
    }
}
