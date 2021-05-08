package com.university.chess.provider.impl;

import com.university.chess.model.Color;
import com.university.chess.provider.ColorProvider;

public class FieldColorProvider implements ColorProvider {

    @Override
    public Color getByIndex(final int index) {
        return isEven(index)
                ? Color.WHITE
                : Color.BLACK;
    }

    private boolean isEven(final int number) {
        return number % 2 == 0;
    }
}
