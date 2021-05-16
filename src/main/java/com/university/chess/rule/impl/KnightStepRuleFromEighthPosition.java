package com.university.chess.rule;

import com.university.chess.model.FieldPosition;

public class KnightStepRuleFromEighthPosition implements KnightStepRule {

    @Override
    public boolean isValid(FieldPosition target) {
        if ((target.getPositionX() == 0 && target.getPositionY() == 0) || (target.getPositionX() == 0 && target.getPositionY() == 2)) {
            return true;
        }
        return false;
    }
}
