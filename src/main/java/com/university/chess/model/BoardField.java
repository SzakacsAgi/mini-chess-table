package com.university.chess.model;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * A mezo oszlop es sor erteket valmint a hozzajuk tartozo babut adja meg.
 */
@Data
@AllArgsConstructor
public class BoardField {

    private int positionX;
    private int positionY;
    private FieldValue fieldValue;
}
