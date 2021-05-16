package com.university.chess.rule;

import com.university.chess.model.FieldPosition;

public class KnightStepRuleFromFirstPosition implements KnightStepRule {

    @Override
    public boolean isValid(FieldPosition target) {
        if ((target.getPositionX() == 1 && target.getPositionY() == 2) || (target.getPositionX() == 2 && target.getPositionY() == 1)) {
            return true;
        }
        return false;
    }
}
