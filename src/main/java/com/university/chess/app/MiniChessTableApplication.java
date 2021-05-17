package com.university.chess.app;

import com.university.chess.config.BoardConfig;
import com.university.chess.controller.MainMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MiniChessTableApplication extends Application {
    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void init() {}

    @Override
    public void start(final Stage primaryStage) {
        final Scene scene = new Scene(new MainMenuController(primaryStage).getView());
        primaryStage.setScene(scene);
        primaryStage.setWidth(BoardConfig.SCENE_SIZE);
        primaryStage.setHeight(BoardConfig.SCENE_SIZE);
        primaryStage.show();
    }
}