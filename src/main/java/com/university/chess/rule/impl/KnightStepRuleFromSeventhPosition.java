package com.university.chess.rule;

import com.university.chess.model.FieldPosition;

public class KnightStepRuleFromSeventhPosition implements KnightStepRule {

    @Override
    public boolean isValid(FieldPosition target) {
        if ((target.getPositionX() == 0 && target.getPositionY() == 1) || (target.getPositionX() == 1 && target.getPositionY() == 2)) {
            return true;
        }
        return false;
    }
}
