package com.university.chess.factory;

import com.university.chess.config.FileConfig;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewFactory {

    public ImageView create(final String fileName)  {
        final var imgView = new ImageView(loadImage(fileName));
        imgView.setFitHeight(FileConfig.IMG_SIZE);
        imgView.setFitWidth(FileConfig.IMG_SIZE);

        return imgView;
    }

    private Image loadImage(final String fileName)  {
        return new Image(getClass().getResource("/" + fileName).toExternalForm());
    }
}
