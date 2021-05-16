package com.university.chess.database;

import com.university.chess.model.BoardField;
import com.university.chess.model.FieldPosition;
import com.university.chess.model.FieldValue;
import com.university.chess.model.StepHistory;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultProducers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StepHistoryRepository {

    private final Jdbi jdbi = JdbiProvider.getInstance();

    public void insert(final FieldPosition fromFieldPosition, final FieldPosition toFieldPosition, final FieldValue fieldValue)  {
        jdbi.useHandle(handle -> {
            handle.createUpdate("INSERT INTO STEP_HISTORY (fromPositionX, fromPositionY, toPositionX, toPositionY, figureValue) VALUES (:fromPositionX, :fromPositionY, :toPositionX, :toPositionY, :figureValue)")
                    .bind("fromPositionX", fromFieldPosition.getPositionX())
                    .bind("fromPositionY", fromFieldPosition.getPositionY())
                    .bind("toPositionX", toFieldPosition.getPositionX())
                    .bind("toPositionY", toFieldPosition.getPositionY())
                    .bind("figureValue", fieldValue)
                    .execute();
        });
    }

    public List<StepHistory> findAll() {
        var result = new ArrayList<StepHistory>();
        jdbi.useHandle(handle -> {
            result.addAll(handle.createQuery("SELECT fromPositionX, fromPositionY, toPositionX, toPositionY, figureValue FROM STEP_HISTORY")
                    .execute(ResultProducers.returningResults())
                    .map((rs, ctx) -> StepHistory.builder()
                            .fieldValue(FieldValue.valueOf(rs.getString("figureValue")))
                            .fromPositionX(rs.getInt("fromPositionX"))
                            .fromPositionY(rs.getInt("fromPositionY"))
                            .toPositionX(rs.getInt("toPositionX"))
                            .toPositionY(rs.getInt("toPositionY"))
                            .build()).list());
        });
        return result;
    }

    public Optional<StepHistory> findByLastStep() {
        var result = new ArrayList<StepHistory>();
        jdbi.useHandle(handle -> {
            result.addAll(handle.createQuery("SELECT TOP 1 fromPositionX, fromPositionY, toPositionX, toPositionY, figureValue FROM STEP_HISTORY ORDER BY id DESC")
                    .execute(ResultProducers.returningResults())
                    .map((rs, ctx) -> StepHistory.builder()
                            .fieldValue(FieldValue.valueOf(rs.getString("figureValue")))
                            .fromPositionX(rs.getInt("fromPositionX"))
                            .fromPositionY(rs.getInt("fromPositionY"))
                            .toPositionX(rs.getInt("toPositionX"))
                            .toPositionY(rs.getInt("toPositionY"))
                            .build()).list());
        });
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }
}
