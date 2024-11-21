package com.example.taskexecutor.misc;

import com.example.taskexecutor.controller.ClickRecordController;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import javafx.scene.control.TableView;

public class GlobalKeyListener implements NativeKeyListener {

    private final ClickRecordController clickRecordController;
    private final TableView<Action> tableView;

    public GlobalKeyListener(final ClickRecordController clickRecordController, final TableView<Action> tableView) {
        this.clickRecordController = clickRecordController;
        this.tableView = tableView;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent event) {

        if (event.getKeyCode() == NativeKeyEvent.VC_F11) {
            try {
                clickRecordController.start(this.tableView);
            } catch (InterruptedException e) {
                System.out.println("START FAILED!");
                e.printStackTrace();
            }
        }

        if (event.getKeyCode() == NativeKeyEvent.VC_F12) {
            clickRecordController.stop();
        }
    }
}
