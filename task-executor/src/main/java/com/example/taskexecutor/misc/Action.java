package com.example.taskexecutor.misc;

import com.example.taskexecutor.enums.ActionType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


// TODO maybe create sub actions for MouseClickAction and KeyboardPressAction
public class Action {

    public StringProperty screen_x = new SimpleStringProperty();
    public StringProperty screen_y = new SimpleStringProperty();
    public StringProperty delay = new SimpleStringProperty();
    public StringProperty key = new SimpleStringProperty();
    public ActionType type;

    public Action() {

    }

    public void execute() throws InterruptedException {
        System.out.println("Executing action!");

//        if (this.type == ActionType.KEYBOARD_PRESS) {
//            try {
//                KeyboardPressController.press(key);
//            } catch (AWTException exception) {
//                System.out.println("There was an error! " + exception);
//            }
//        } else {
//            try {
//                ClickController.click(screen_x, screen_y);
//            } catch (AWTException exception) {
//                System.out.println("There was an error! " + exception);
//            }
//        }
//
//        Thread.sleep(this.delay);
    }
}
