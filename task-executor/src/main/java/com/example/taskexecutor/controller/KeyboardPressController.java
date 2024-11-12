package com.example.taskexecutor.controller;

import java.awt.*;

public class KeyboardPressController {

    public static void press(int key) throws AWTException {
        Robot r = new Robot();
        r.keyPress(key);
        r.keyRelease(key);
    }
}
