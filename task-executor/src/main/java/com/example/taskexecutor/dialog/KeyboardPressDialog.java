package com.example.taskexecutor.dialog;

import com.example.taskexecutor.builder.PaneBuilder;
import com.example.taskexecutor.enums.ActionType;
import com.example.taskexecutor.misc.Action;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class KeyboardPressDialog extends Dialog<Action> {

    private Action action;

    private TextField key = new TextField();
    private TextField delay = new TextField();

    public KeyboardPressDialog() {
        super();
        this.setTitle("Register key press operation");
        this.action = new Action();
        this.action.type = ActionType.KEYBOARD_PRESS;
        buildUI();
        setPropertyBindings();
        setResultConverter();
    }

    private void buildUI() {
        Pane pane = PaneBuilder.keyPressPane(this.key, this.delay);
        getDialogPane().setContent(pane);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // TODO validate and make fields numeric
//        Button button = (Button) getDialogPane().lookupButton(ButtonType.OK);
//        button.addEventFilter(ActionEvent.ACTION, actionEvent -> {
//            System.out.println(actionEvent);
//        });
    }

    private void setPropertyBindings() {
//        this.key.textProperty().bindBidirectional(this.action.key);
//        this.delay.textProperty().bindBidirectional(this.action.delay);
    }

    private void setResultConverter() {
        setResultConverter(param -> {
            if (param == ButtonType.OK) {
                return this.action;
            } else {
                return null;
            }
        });
    }
}
