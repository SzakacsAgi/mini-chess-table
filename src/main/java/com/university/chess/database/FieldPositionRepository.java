package com.university.chess.database;

import com.university.chess.model.FieldPosition;
import com.university.chess.model.FieldValue;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultProducers;

import java.util.concurrent.atomic.AtomicBoolean;

public class FieldPositionRepository {

    private final Jdbi jdbi = JdbiProvider.getInstance();

    public void insert(final FieldPosition fieldPosition, final FieldValue fieldValue)  {
        jdbi.useHandle(handle -> {
            handle.createUpdate("INSERT INTO BOARD_FIELD (positionX, positionY, figureValue) VALUES (:positionX, :positionY, :figureValue)")
                    .bind("positionX", fieldPosition.getPositionX())
                    .bind("positionY", fieldPosition.getPositionY())
                    .bind("figureValue", fieldValue)
                    .execute();
        });
    }

    public boolean isEmptyPosition(final FieldPosition fieldPosition) {
        AtomicBoolean result = new AtomicBoolean(false);
        jdbi.useHandle(handle -> {
            result.set(handle.createQuery("SELECT figureValue FROM BOARD_FIELD WHERE positionX = :positionX AND positionY = :positionY")
                    .bind("positionX", fieldPosition.getPositionX())
                    .bind("positionY", fieldPosition.getPositionY())
                    .execute(ResultProducers.returningResults()).mapTo(FieldValue.class).list()
                    .stream()
                    .findFirst()
                    .filter(fieldValue -> fieldValue == FieldValue.EMPTY)
                    .isPresent());
        });
        return result.get();
    }
}
