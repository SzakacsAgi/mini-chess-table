package com.university.chess.model;

import lombok.Data;
/**
 * Megadja, hogy hol helyezkedik az adott babu.
 */
@Data
public class Position {

    private final int rowIndex;
    private final int columnIndex;
}
