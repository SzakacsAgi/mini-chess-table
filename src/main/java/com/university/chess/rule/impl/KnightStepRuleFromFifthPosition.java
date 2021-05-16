package com.university.chess.rule.impl;

import com.university.chess.model.FieldPosition;
import com.university.chess.rule.KnightStepRule;

public class KnightStepRuleFromFifthPosition implements KnightStepRule {

    @Override
    public boolean isValid(FieldPosition target) {
        return false;
    }
}
