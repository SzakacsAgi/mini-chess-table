package com.university.chess.database.populator;

import com.university.chess.database.FieldPositionRepository;
import com.university.chess.model.FieldPosition;
import com.university.chess.model.FieldValue;
import lombok.AllArgsConstructor;

import java.util.stream.IntStream;

@AllArgsConstructor
public class BoardFieldPopulator {

    private final FieldPositionRepository repository;

    public void populateDatabase() {
        IntStream.range(0, 1)
                .boxed()
                .flatMap(x -> IntStream.range(0, 3)
                        .mapToObj(y -> new FieldPosition(x, y)))
                .forEach(fieldPosition -> repository.insert(fieldPosition, FieldValue.WHITE_KNIGHT));

        IntStream.range(1, 2)
                .boxed()
                .flatMap(x -> IntStream.range(0, 3)
                        .mapToObj(y -> new FieldPosition(x, y)))
                .forEach(fieldPosition -> repository.insert(fieldPosition, FieldValue.EMPTY));

        IntStream.range(2, 3)
                .boxed()
                .flatMap(x -> IntStream.range(0, 3)
                        .mapToObj(y -> new FieldPosition(x, y)))
                .forEach(fieldPosition -> repository.insert(fieldPosition, FieldValue.BLACK_KNIGHT));


    }
}
