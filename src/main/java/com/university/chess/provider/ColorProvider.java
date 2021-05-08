package com.university.chess.provider;

import com.university.chess.model.Color;

public interface ColorProvider {

    Color getByIndex(int index);
}
