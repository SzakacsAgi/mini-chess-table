package com.university.chess.model;

import lombok.Data;
/**
 * Megadja, hogy hol helyezkedik az adott babu.
 */
@Data
public class FieldPosition {

    private final int positionX;
    private final int positionY;
}
