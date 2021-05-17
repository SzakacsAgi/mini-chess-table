package com.university.chess.controller;

import com.university.chess.controller.view.MainMenuView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class MainMenuController implements EventHandler {

    private final MainMenuView view;

    public MainMenuController(final Stage primaryStage) {
        this.view = new MainMenuView(new ChessTableController(primaryStage), new ExitController());
    }

    @Override
    public void handle(final Event event) {
    }

    public MainMenuView getView() {
        return view;
    }
}
