package com.university.chess.rule;

import com.university.chess.database.StepHistoryRepository;
import com.university.chess.model.FieldValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NextStepChecker {

    private final StepHistoryRepository repository;

    public boolean isNextStepForWhiteKnight() {
        return repository.findByLastStep()
                .map(stepHistory -> stepHistory.getFieldValue() == FieldValue.BLACK_KNIGHT)
                .orElse(true);
    }

    public boolean isNextStepForBlackKnight() {
        return repository.findByLastStep()
                .map(stepHistory -> stepHistory.getFieldValue() == FieldValue.WHITE_KNIGHT)
                .orElse(false);
    }
}
