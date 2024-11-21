package com.example.taskexecutor.enums;

public enum ActionType {
    LEFT_MOUSE_CLICK("Left click"),
    RIGHT_MOUSE_CLICK("Right click"),
    KEYBOARD_PRESS("Key press");

    private final String name;

    ActionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}