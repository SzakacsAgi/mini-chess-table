package com.university.chess.database;

import com.university.chess.model.Coordination;
import org.jdbi.v3.core.Jdbi;

public class BoardFieldRepository {

    private final Jdbi jdbi = JdbiProvider.getInstance();

    public void insert(final Coordination coordination)  {
        jdbi.useHandle(handle -> {
            handle.createUpdate("INSERT INTO FIELD_POSITION (positionX, positionY) VALUES (:positionX, :positionY)")
                    .bind("positionX", coordination.getPositionX())
                    .bind("positionY", coordination.getPositionY())
                    .execute();
        });
    }
}
