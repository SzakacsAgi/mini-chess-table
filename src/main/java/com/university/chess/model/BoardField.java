package com.university.chess.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardField {

    private int positionX;
    private int positionY;
    private FieldValue fieldValue;
}
