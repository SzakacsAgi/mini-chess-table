package com.university.chess.provider.impl;

import com.university.chess.model.FieldColor;
import com.university.chess.provider.ColorProvider;

public class FieldColorProvider implements ColorProvider {

    @Override
    public FieldColor getByIndex(final int index) {
        return isEven(index)
                ? FieldColor.WHITE
                : FieldColor.BLACK;
    }

    private boolean isEven(final int number) {
        return number % 2 == 0;
    }
}
