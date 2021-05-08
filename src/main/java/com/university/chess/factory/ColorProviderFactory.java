package com.university.chess.factory;

import com.university.chess.provider.ColorProvider;
import com.university.chess.provider.impl.FieldColorProvider;

import java.util.function.Supplier;

public class ColorProviderFactory implements Supplier<ColorProvider> {

    @Override
    public ColorProvider get() {
        return new FieldColorProvider();
    }
}
