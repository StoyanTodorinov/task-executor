package com.example.taskexecutor.misc;

import com.example.taskexecutor.enums.ActionType;

// TODO maybe create sub actions for MouseClickAction and KeyboardPressAction
public class Action {

    public int screen_x = 0;
    public int screen_y = 0;
    public int delay = 1;
    public String key = "-";
    public ActionType type;

    public Action() {}



    // TODO fix and run
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

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }
}
