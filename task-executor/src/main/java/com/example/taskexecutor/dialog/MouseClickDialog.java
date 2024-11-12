package com.example.taskexecutor.dialog;

import com.example.taskexecutor.builder.PaneBuilder;
import com.example.taskexecutor.enums.ActionType;
import com.example.taskexecutor.misc.Action;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class MouseClickDialog extends Dialog<Action> {

    private Action action;

    private TextField screen_x = new TextField();
    private TextField screen_y = new TextField();
    private TextField delay = new TextField();

    public MouseClickDialog() {
        super();
        this.setTitle("Register mouse click operation");
        this.action = new Action();
        this.action.type = ActionType.MOUSE_CLICK;
        buildUI();
        setPropertyBindings();
        setResultConverter();
    }

    private void buildUI() {
        Pane pane = PaneBuilder.mouseClickPane(this.screen_x, this.screen_y, this.delay);
        getDialogPane().setContent(pane);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // TODO validate and make fields numeric
//        Button button = (Button) getDialogPane().lookupButton(ButtonType.OK);
//        button.addEventFilter(ActionEvent.ACTION, actionEvent -> {
//            System.out.println(actionEvent);
//        });
    }

    private void setPropertyBindings() {
        this.screen_x.textProperty().bindBidirectional(this.action.screen_x);
        this.screen_y.textProperty().bindBidirectional(this.action.screen_y);
        this.delay.textProperty().bindBidirectional(this.action.delay);
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
