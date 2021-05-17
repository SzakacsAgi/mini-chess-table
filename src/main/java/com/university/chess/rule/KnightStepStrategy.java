package com.university.chess.rule;

import com.university.chess.model.FieldPosition;
import com.university.chess.rule.impl.*;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum KnightStepStrategy {

    FIRST_POSITION(0, 0, new KnightStepRuleFromFirstPosition()),
    SECOND_POSITION(0, 1, new KnightStepRuleFromSecondPosition()),
    THIRD_POSITION(0, 2, new KnightStepRuleFromThirdPosition()),
    FOURTH_POSITION(1, 0, new KnightStepRuleFromFourthPosition()),
    FIFTH_POSITION(1, 1, new KnightStepRuleFromFifthPosition()),
    SIXTH_POSITION(1, 2, new KnightStepRuleFromSixthPosition()),
    SEVENTH_POSITION(2, 0, new KnightStepRuleFromSeventhPosition()),
    EIGHTH_POSITION(2, 1, new KnightStepRuleFromEighthPosition()),
    NINTH_POSITION(2, 2, new KnightStepRuleFromNinthPosition());

    KnightStepStrategy(final int positionX, final int positionY, final KnightStepRule knightStepRule) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.knightStepRule = knightStepRule;
    }

    private final int positionX;
    private final int positionY;
    private final KnightStepRule knightStepRule;

    public static KnightStepStrategy from(final FieldPosition fieldPosition) {
        return Arrays.stream(KnightStepStrategy.values())
                .filter(knightStepStrategy -> knightStepStrategy.getPositionX() == fieldPosition.getPositionX() && knightStepStrategy.getPositionY() == fieldPosition.getPositionY())
                .findFirst()
                .get();
    }
}
