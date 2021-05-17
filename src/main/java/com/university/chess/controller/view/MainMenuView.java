package com.university.chess.controller.view;

import com.university.chess.controller.ChessTableController;
import com.university.chess.controller.ExitController;
import com.university.chess.controller.MainMenuController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.text.MessageFormat;

public class MainMenuView extends HBox {

    private static final String STYLE_PATTERN = "-fx-background-color: {0};";

    private final Button newGameButton = new Button("Uj jatek");
    private final Button exitButton = new Button("Kilepes");

    public MainMenuView(final ChessTableController chessTableController, final ExitController exitController) {
        newGameButton.setOnAction(chessTableController);
        exitButton.setOnAction(exitController);

        Text text = new Text();
        text.setText("Huszarfelcsereles\n" + "\n" +
                "Jatekszabalyzat: \n" +
                "Lepj felvaltva a feher es fekete huszarokkal, amig a feketek az elso sorba, a feherek pedig az utolso sorba nem kerulnek. Kattints az uj jatekra a jatek indulasahoz! \n" + "A feherrel lepj eloszor!\n" + "\n" +
                "Sok sikert! :)\n");
        text.setTextAlignment(TextAlignment.CENTER);
        VBox vbox = new VBox(40);

        vbox.getChildren().addAll(newGameButton, text, exitButton);
        vbox.setAlignment(Pos.CENTER);

        this.getChildren().add(vbox);
        this.setStyle(MessageFormat.format(STYLE_PATTERN, "LIGHTBLUE"));
    }

    public Button getNewGameButton() {
        return newGameButton;
    }
}
