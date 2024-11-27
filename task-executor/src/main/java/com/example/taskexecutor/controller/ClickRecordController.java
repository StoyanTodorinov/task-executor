package com.example.taskexecutor.controller;

import com.example.taskexecutor.enums.AppState;
import com.example.taskexecutor.misc.Action;
import com.example.taskexecutor.runner.OperationsRunner;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClickRecordController {

    private Thread operationsRunnerThread;
    private final SimpleObjectProperty<AppState> state = new SimpleObjectProperty<>(AppState.IDLE);
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
        System.out.println(state.get());
        if (state.get() == AppState.RUNNING) {
            throw new RuntimeException("CANNOT START ACTION EXECUTION MULTIPLE TIMES!");
        }

        if (this.actions.isEmpty()) {
            throw new RuntimeException("START FAILED, NO ACTIONS!");
        }

        setState(AppState.RUNNING);
        OperationsRunner runner = new OperationsRunner(this, tableView);
        Thread thread = new Thread(runner);
        thread.start();

        // terminate previous execution if needed before setting a reference to the new thread
        terminateThreadIfAlive();
        operationsRunnerThread = thread;
    }

    public void stop() {
        if (state.get() == AppState.STOPPED) {
            throw new RuntimeException("ACTION EXECUTION IS ALREADY STOPPED!");
        }

        System.out.println("PROGRAM STOP");
        setState(AppState.STOPPED);
        terminateThreadIfAlive();
    }

    public SimpleObjectProperty<AppState> getState() {
        return this.state;
    }

    private void setState(AppState newState) {
        this.state.set(newState);
    }

    private void terminateThreadIfAlive() {
        if (Objects.nonNull(operationsRunnerThread) && operationsRunnerThread.isAlive()) {
            operationsRunnerThread.interrupt();
        }
    }
}
