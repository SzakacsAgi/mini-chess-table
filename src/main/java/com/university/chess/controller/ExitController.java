package com.university.chess.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExitController implements EventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExitController.class);

    @Override
    public void handle(Event event) {
        LOGGER.info("Closing the application");
        System.exit(0);
    }
}
