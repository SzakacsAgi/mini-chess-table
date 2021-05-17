package com.university.chess.app;

import com.university.chess.config.BoardConfig;
import com.university.chess.controller.MainMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiniChessTableApplication extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(MiniChessTableApplication.class);

    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void init() {}

    @Override
    public void start(final Stage primaryStage) {
        LOGGER.info("Starting the application...");

        final Scene scene = new Scene(new MainMenuController(primaryStage).getView());
        primaryStage.setScene(scene);
        primaryStage.setWidth(BoardConfig.SCENE_SIZE);
        primaryStage.setHeight(BoardConfig.SCENE_SIZE);
        primaryStage.show();
    }
}