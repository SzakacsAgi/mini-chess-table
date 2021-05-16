package com.university.chess.provider;

import com.university.chess.model.FieldColor;

public interface ColorProvider {

    FieldColor getByIndex(int index);
}
