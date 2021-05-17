package com.university.chess.controller;

import com.university.chess.config.BoardConfig;
import com.university.chess.config.FileConfig;
import com.university.chess.database.FieldPositionRepository;
import com.university.chess.database.StepHistoryRepository;
import com.university.chess.database.factory.DatabaseTableFactory;
import com.university.chess.database.init.DatabaseInitializer;
import com.university.chess.database.populator.BoardFieldPopulator;
import com.university.chess.factory.ColorProviderFactory;
import com.university.chess.factory.ImageViewFactory;
import com.university.chess.model.FieldPosition;
import com.university.chess.model.FieldValue;
import com.university.chess.provider.ColorProvider;
import com.university.chess.rule.EmptyPositionChecker;
import com.university.chess.rule.EndGameChecker;
import com.university.chess.rule.NextStepChecker;
import com.university.chess.rule.ValidKnightStepVerifier;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static com.university.chess.model.FieldValue.EMPTY;

@AllArgsConstructor
@Getter
public class ChessTableController implements EventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChessTableController.class);

    private static final String STYLE_PATTERN = "-fx-background-color: {0};";

    private final ColorProvider colorProvider = new ColorProviderFactory().get();
    private final ImageViewFactory imageViewFactory = new ImageViewFactory();

    private final Stage primaryStage;

    @Override
    public void handle(final Event event2) {
        LOGGER.info("Loading chess table view....");

        new DatabaseInitializer(new DatabaseTableFactory(), new BoardFieldPopulator(new FieldPositionRepository())).init();

        Map<String, StackPane> draggedAndDroppedFields = new HashMap<>();
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
                square.setOnMouseReleased(event -> square.setMouseTransparent(false));

                square.setOnDragDetected(event -> {
                    square.startFullDrag();
                    draggedAndDroppedFields.put("SOURCE", square);
                });


                square.setOnMouseDragReleased(event -> {
                    draggedAndDroppedFields.put("TARGET", square);

                    final var source = draggedAndDroppedFields.get("SOURCE");
                    final var target = square;

                    final var sourceFieldPosition = new FieldPosition(GridPane.getRowIndex(source), GridPane.getColumnIndex(source));
                    final var targetFieldPosition = new FieldPosition(GridPane.getRowIndex(target), GridPane.getColumnIndex(target));

                    final var movingKnight =  new FieldPositionRepository().findByPosition(new FieldPosition(GridPane.getRowIndex(source), GridPane.getColumnIndex(source))).get().getFieldValue();

                    LOGGER.info("Source field={}, target field={}, knight={}", sourceFieldPosition, targetFieldPosition, movingKnight);

                    if (new ValidKnightStepVerifier(new EmptyPositionChecker(new FieldPositionRepository()), new NextStepChecker(new StepHistoryRepository())).isValidStep(sourceFieldPosition, targetFieldPosition, movingKnight)) {
                        new FieldPositionRepository().updatePositionValue(new FieldPosition(GridPane.getRowIndex(target), GridPane.getColumnIndex(target)), movingKnight);
                        new FieldPositionRepository().updatePositionValue(new FieldPosition(GridPane.getRowIndex(source), GridPane.getColumnIndex(source)), EMPTY);
                        source.getChildren().clear();
                        switch (movingKnight) {
                            case BLACK_KNIGHT -> target.getChildren().add(imageViewFactory.create(FileConfig.CHESS_KNIGHT_BLACK_PNG));
                            case WHITE_KNIGHT -> target.getChildren().add(imageViewFactory.create(FileConfig.CHESS_KNIGHT_WHITE_PNG));
                        }

                        new StepHistoryRepository().insert(sourceFieldPosition, targetFieldPosition, movingKnight);

                        LOGGER.info("Source field={}, target field={}, knight={} with successful step", sourceFieldPosition, targetFieldPosition, movingKnight);


                        if (new EndGameChecker(new FieldPositionRepository()).isEndGame()) {
                            LOGGER.info("Game is over...");
                            final StepHistoryController controllerB = new StepHistoryController(primaryStage);
                            final Scene scene = new Scene(controllerB.getView());
                            primaryStage.setScene(scene);
                        }
                    }
                });
            }
        }

        IntStream.range(0, BoardConfig.SIZE).forEach($ -> {
            root.getColumnConstraints().add(new ColumnConstraints(BoardConfig.MIN_FIELD_SIZE, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(BoardConfig.MIN_FIELD_SIZE, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        });

        primaryStage.setScene(new Scene(root));
    }
}
