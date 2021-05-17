package com.university.chess.app;

import com.university.chess.config.BoardConfig;
import com.university.chess.config.FileConfig;
import com.university.chess.database.FieldPositionRepository;
import com.university.chess.database.JdbiProvider;
import com.university.chess.database.QueryProvider;
import com.university.chess.database.StepHistoryRepository;
import com.university.chess.database.factory.DatabaseTableFactory;
import com.university.chess.database.populator.BoardFieldPopulator;
import com.university.chess.factory.ColorProviderFactory;
import com.university.chess.factory.ImageViewFactory;
import com.university.chess.model.FieldPosition;
import com.university.chess.model.Position;
import com.university.chess.model.FieldValue;
import com.university.chess.model.StepHistory;
import com.university.chess.provider.ColorProvider;
import com.university.chess.rule.EmptyPositionChecker;
import com.university.chess.rule.EndGameChecker;
import com.university.chess.rule.NextStepChecker;
import com.university.chess.rule.ValidKnightStepVerifier;
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
import org.jdbi.v3.core.statement.Query;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.IntStream;

import static com.university.chess.model.FieldValue.*;

public class MiniChessTableApplication extends Application {

    private static final String STYLE_PATTERN = "-fx-background-color: {0};";

    private final ColorProvider colorProvider = new ColorProviderFactory().get();
    private final ImageViewFactory imageViewFactory = new ImageViewFactory();


    @Override
    public void start(final Stage primaryStage) {
        new DatabaseTableFactory().createBoardFieldTableQuery();
        new DatabaseTableFactory().createStepHistoryTableQuery();

        JdbiProvider.getInstance().useHandle(handle -> {

            new BoardFieldPopulator(new FieldPositionRepository()).populateDatabase();

            Query query = handle.createQuery(QueryProvider.SELECT_ALL_PROJECT_QUERY);
            List<Map<String, Object>> results = query.mapToMap().list();
            results.forEach(System.out::println);
        });

        new FieldPositionRepository().findAll().forEach(System.out::println);


        Map<String, StackPane> fieldPositions = new HashMap<>();
        final var root = new GridPane();
        for (var row = 0; row < BoardConfig.SIZE; row++) {
            for (var column = 0; column < BoardConfig.SIZE; column ++) {
                final var square = new StackPane();

                new FieldPositionRepository().findByPosition(new FieldPosition(row, column))
                        .ifPresent(boardField -> {
                            switch (boardField.getFieldValue()) {
                            case BLACK_KNIGHT -> square.getChildren().add(imageViewFactory.create(FileConfig.CHESS_KNIGHT_BLACK_PNG));
                            case WHITE_KNIGHT -> square.getChildren().add(imageViewFactory.create(FileConfig.CHESS_KNIGHT_WHITE_PNG));
                            }
                        });

                final var color = colorProvider.getByIndex(row + column);
                square.setStyle(MessageFormat.format(STYLE_PATTERN, color));
                root.add(square, column, row);

                // Add mouse event handlers for the source

                square.setOnMouseReleased(event -> {
                    square.setMouseTransparent(false);
                });

                square.setOnDragDetected(event -> {
                    square.startFullDrag();
                    fieldPositions.put("SOURCE", square);
                });


                square.setOnMouseDragReleased(event -> {
                    fieldPositions.put("TARGET", square);

                    final var source = fieldPositions.get("SOURCE");
                    final var target = square;

                    final var sourceFieldPosition = new FieldPosition(GridPane.getRowIndex(source), GridPane.getColumnIndex(source));
                    final var targetFieldPosition = new FieldPosition(GridPane.getRowIndex(target), GridPane.getColumnIndex(target));

                    FieldValue movingKnight =  new FieldPositionRepository().findByPosition(new FieldPosition(GridPane.getRowIndex(source), GridPane.getColumnIndex(source))).get().getFieldValue();

                    if (new ValidKnightStepVerifier(new EmptyPositionChecker(new FieldPositionRepository()), new NextStepChecker(new StepHistoryRepository())).isValidStep(sourceFieldPosition, targetFieldPosition, movingKnight)) {
                        new FieldPositionRepository().updatePositionValue(new FieldPosition(GridPane.getRowIndex(target), GridPane.getColumnIndex(target)), movingKnight);
                        new FieldPositionRepository().updatePositionValue(new FieldPosition(GridPane.getRowIndex(source), GridPane.getColumnIndex(source)), EMPTY);
                        source.getChildren().clear();
                        switch (movingKnight) {
                            case BLACK_KNIGHT -> target.getChildren().add(imageViewFactory.create(FileConfig.CHESS_KNIGHT_BLACK_PNG));
                            case WHITE_KNIGHT -> target.getChildren().add(imageViewFactory.create(FileConfig.CHESS_KNIGHT_WHITE_PNG));
                        }

                        new StepHistoryRepository().insert(sourceFieldPosition, targetFieldPosition, movingKnight);
                        
                        if (new EndGameChecker(new FieldPositionRepository()).isEndGame()) {
                            System.exit(0);
                        }
                    }


                    new FieldPositionRepository().findAll().forEach(System.out::println);

                    new StepHistoryRepository().findAll().forEach(System.out::println);

                });

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