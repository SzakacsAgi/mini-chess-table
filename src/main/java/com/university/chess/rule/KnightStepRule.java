package com.university.chess.rule;

import com.university.chess.model.FieldPosition;

public interface KnightStepRule {

    boolean isValid(FieldPosition source, FieldPosition target);
}
