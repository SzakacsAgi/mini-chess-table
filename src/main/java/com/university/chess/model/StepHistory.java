package com.university.chess.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StepHistory {

    private int fromPositionX;
    private int fromPositionY;
    private int toPositionX;
    private int toPositionY;
    private FieldValue fieldValue;
}
