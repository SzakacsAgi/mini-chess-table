package com.university.chess.rule;

import com.university.chess.database.FieldPositionRepository;
import com.university.chess.model.BoardField;
import com.university.chess.model.FieldValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EndGameChecker {

    private final FieldPositionRepository repository;

    public boolean isEndGame() {
        final boolean firstRowContainsOnlyBlackNights = repository.findFirstNRecords(3)
                .stream()
                .map(BoardField::getFieldValue)
                .allMatch(fieldValue -> fieldValue == FieldValue.BLACK_KNIGHT);

        final boolean lastRowContainsOnlyWhiteKnights = repository.findLastNRecords(3)
                .stream()
                .map(BoardField::getFieldValue)
                .allMatch(fieldValue -> fieldValue == FieldValue.WHITE_KNIGHT);

        return firstRowContainsOnlyBlackNights && lastRowContainsOnlyWhiteKnights;
    }
}
