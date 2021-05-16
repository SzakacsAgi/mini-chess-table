package com.university.chess.rule;

import com.university.chess.model.FieldPosition;

public class KnightStepRuleFromSixthPosition implements KnightStepRule {

    @Override
    public boolean isValid(FieldPosition target) {
        if ((target.getPositionX() == 0 && target.getPositionY() == 0) || (target.getPositionX() == 2 && target.getPositionY() == 0)) {
            return true;
        }
        return false;
    }
}
