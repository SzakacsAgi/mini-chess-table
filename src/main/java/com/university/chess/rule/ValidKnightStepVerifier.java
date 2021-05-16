package com.university.chess.rule;

import com.university.chess.model.FieldPosition;
import com.university.chess.model.FieldValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidKnightStepVerifier {

    private final EmptyPositionChecker emptyPositionChecker;
    private final NextStepChecker nextStepChecker;

    public boolean isValidStep(final FieldPosition source, final FieldPosition target, final FieldValue fieldValue)  {
        return emptyPositionChecker.isEmpty(target)
                && ((nextStepChecker.isNextStepForBlackKnight() && fieldValue == FieldValue.BLACK_KNIGHT) || (nextStepChecker.isNextStepForWhiteKnight() && fieldValue == FieldValue.WHITE_KNIGHT))
                && KnightStepStrategy.from(source).getKnightStepRule().isValid(target);
    }
}
