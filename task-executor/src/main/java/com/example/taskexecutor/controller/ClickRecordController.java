package com.example.taskexecutor.controller;

import com.example.taskexecutor.misc.Action;

import java.util.ArrayList;
import java.util.List;

public class ClickRecordController {

    private final List<Action> actions = new ArrayList<>();

    public List<Action> getActions() {
        return  actions;
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }
}
