package com.university.chess.controller;

import javafx.event.Event;
import javafx.event.EventHandler;

public class ExitController implements EventHandler {

    @Override
    public void handle(Event event) {
        System.exit(0);
    }
}
