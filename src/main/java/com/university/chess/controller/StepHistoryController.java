package com.university.chess.controller;

import com.university.chess.controller.view.StepHistoryView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class StepHistoryController implements EventHandler {

    private final Stage primaryStage;
    private final StepHistoryView view;

    @Override
    public void handle(final Event event) {

    }

    public StepHistoryController(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.view = new StepHistoryView(this, new MainMenuController(primaryStage), new ExitController());
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public StepHistoryView getView() {
        return view;
    }
}
