package com.university.chess.controller.view;

import com.university.chess.controller.ExitController;
import com.university.chess.controller.MainMenuController;
import com.university.chess.controller.StepHistoryController;
import com.university.chess.database.StepHistoryRepository;
import com.university.chess.model.StepHistory;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class StepHistoryView extends HBox {

    public StepHistoryView(final StepHistoryController stepHistoryController, final MainMenuController mainMenuController, final ExitController exitController) {
        final var mainMenuButton = new Button("Fomenu");
        mainMenuButton.setOnAction(event -> {
            final Stage primaryStage = stepHistoryController.getPrimaryStage();
            final Scene scene = new Scene(mainMenuController.getView());
            primaryStage.setScene(scene);
        });

        final var exitButton = new Button("Kilepes");
        exitButton.setOnAction(exitController);

        VBox vbox = new VBox(30);

        vbox.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);

        final List<StepHistory> stepHistories = new StepHistoryRepository().findAll();
        TableView<Integer> table = new TableView<>();
        for (var i = 0; i < stepHistories.size(); i++) {
            table.getItems().add(i);
        }

        TableColumn<Integer, Number> fromPositionX = new TableColumn<>("From Position X");
        fromPositionX.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyIntegerWrapper(stepHistories.stream().map(StepHistory::getFromPositionX).collect(Collectors.toList()).get(rowIndex));
        });

        TableColumn<Integer, Number> fromPositionY = new TableColumn<>("From Position Y");
        fromPositionY.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyIntegerWrapper(stepHistories.stream().map(StepHistory::getFromPositionY).collect(Collectors.toList()).get(rowIndex));
        });

        TableColumn<Integer, Number> toPositionX = new TableColumn<>("To Position X");
        toPositionX.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyIntegerWrapper(stepHistories.stream().map(StepHistory::getToPositionX).collect(Collectors.toList()).get(rowIndex));
        });

        TableColumn<Integer, Number> toPositionY = new TableColumn<>("To Position Y");
        toPositionY.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyIntegerWrapper(stepHistories.stream().map(StepHistory::getToPositionY).collect(Collectors.toList()).get(rowIndex));
        });

        TableColumn<Integer, String> fieldValueColumn = new TableColumn<>("Field value");
        fieldValueColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(stepHistories.stream().map(StepHistory::getFieldValue).map(String::valueOf).collect(Collectors.toList()).get(rowIndex));
        });

        table.getColumns().add(fromPositionX);
        table.getColumns().add(fromPositionY);
        table.getColumns().add(toPositionX);
        table.getColumns().add(toPositionY);
        table.getColumns().add(fieldValueColumn);
        table.minWidth(800);
        table.minHeight(800);
        table.getColumns().forEach(column -> column.setMinWidth(120));

        vbox.getChildren().addAll(mainMenuButton, table, exitButton);
        this.getChildren().add(vbox);
    }
}