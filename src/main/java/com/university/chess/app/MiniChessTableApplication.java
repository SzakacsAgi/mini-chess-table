package com.university.chess.app;

import com.university.chess.config.BoardConfig;
import com.university.chess.factory.ColorProviderFactory;
import com.university.chess.provider.ColorProvider;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.text.MessageFormat;
import java.util.stream.IntStream;

public class MiniChessTableApplication extends Application {

    private static final String STYLE_PATTERN = "-fx-background-color: {0};";

    private final ColorProvider colorProvider = new ColorProviderFactory().get();

    @Override
    public void start(final Stage primaryStage) {
        final var root = new GridPane();
        for (var row = 0; row < BoardConfig.SIZE; row++) {
            for (var column = 0; column < BoardConfig.SIZE; column ++) {
                final var square = new StackPane();
                final var color = colorProvider.getByIndex(row + column);
                square.setStyle(MessageFormat.format(STYLE_PATTERN, color));
                root.add(square, column, row);
            }
        }

        IntStream.range(0, BoardConfig.SIZE).forEach($ -> {
            root.getColumnConstraints().add(new ColumnConstraints(BoardConfig.MIN_FIELD_SIZE, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(BoardConfig.MIN_FIELD_SIZE, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));

        });
        primaryStage.setScene(new Scene(root, BoardConfig.SCENE_SIZE, BoardConfig.SCENE_SIZE));
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}