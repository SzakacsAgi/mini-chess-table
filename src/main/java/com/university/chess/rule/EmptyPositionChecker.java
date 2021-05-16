package com.university.chess.rule;

import com.university.chess.database.FieldPositionRepository;
import com.university.chess.model.FieldPosition;
import com.university.chess.model.FieldValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmptyPositionChecker {

    private final FieldPositionRepository repository;

    public boolean isEmpty(final FieldPosition fieldPosition)  {
        final var fieldValue = repository.findByPosition(fieldPosition).get().getFieldValue();
        return fieldValue == FieldValue.EMPTY;
    }
}
