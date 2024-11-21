package com.example.taskexecutor.misc;

import com.example.taskexecutor.constant.Constants;
import com.example.taskexecutor.controller.ClickController;
import com.example.taskexecutor.controller.ClickRecordController;
import com.example.taskexecutor.controller.KeyboardPressController;
import com.example.taskexecutor.enums.ActionType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.awt.*;

import static java.lang.Integer.MAX_VALUE;

// TODO maybe create sub actions for MouseClickAction and KeyboardPressAction
public class Action {

    public int screen_x;
    public int screen_y;
    public double delay;
    public String key;
    public int keyCode;
    public ActionType type;
    private Button removeBtn = createRemoveButton();
    private final ClickRecordController clickRecordController;
    private final TableView<Action> tableView;

    public Action(int screen_x, int screen_y, ActionType actionType, final ClickRecordController clickRecordController,
                  final TableView<Action> tableView) {
        this.screen_x = screen_x;
        this.screen_y = screen_y;
        this.delay = Constants.DEFAULT_DELAY;
        this.key = Constants.NOT_APPLICABLE;
        this.keyCode = Constants.NOT_SET;
        this.type = actionType;
        this.clickRecordController = clickRecordController;
        this.tableView = tableView;
    }

    public Action(String key, int keyCode, final ClickRecordController clickRecordController,
                  final TableView<Action> tableView) {
        this.screen_x = Constants.NOT_SET;
        this.screen_y = Constants.NOT_SET;
        this.delay = Constants.DEFAULT_DELAY;
        this.key = key;
        this.keyCode = keyCode;
        this.type = ActionType.KEYBOARD_PRESS;
        this.clickRecordController = clickRecordController;
        this.tableView = tableView;
    }

    // TODO fix and run
    public void execute() {
        System.out.println("Executing action!");
        if (this.type == ActionType.KEYBOARD_PRESS) {
            try {
                KeyboardPressController.press(keyCode);
            } catch (AWTException exception) {
                System.out.println("There was an error! " + exception);
            }
        } else if (this.type == ActionType.RIGHT_MOUSE_CLICK) {
            try {
                ClickController.rightClick(screen_x, screen_y);
            } catch (AWTException exception) {
                System.out.println("There was an error! " + exception);
            }
        } else {
            try {
                ClickController.leftClick(screen_x, screen_y);
            } catch (AWTException exception) {
                System.out.println("There was an error! " + exception);
            }
        }
    }

    public int getScreen_x() {
        return screen_x;
    }

    public void setScreen_x(Integer screen_x) {
        this.screen_x = screen_x;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getScreen_y() {
        return screen_y;
    }

    public void setScreen_y(Integer screen_y) {
        this.screen_y = screen_y;
    }

    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    public Button getRemoveBtn() {
        return this.removeBtn;
    }

    public void setRemoveBtn(Button button) {
        this.removeBtn = button;
    }

    // TODO make button take all the space in the column
    private Button createRemoveButton() {
        Button removeBtn = new Button(Constants.REMOVE);
        removeBtn.setMaxWidth(MAX_VALUE);
        removeBtn.setOnAction(_ -> {
            System.out.println("REMOVING...");
            this.clickRecordController.removeAction(this);
            this.tableView.getItems().remove(this);
        });

        return removeBtn;
    }
}
