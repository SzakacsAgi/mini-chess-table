package com.university.chess.database;

import com.university.chess.model.BoardField;
import com.university.chess.model.FieldPosition;
import com.university.chess.model.FieldValue;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.result.ResultProducers;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<BoardField>  findAll() {
        var result = new ArrayList<BoardField>();
        jdbi.useHandle(handle -> {
            result.addAll(handle.createQuery("SELECT positionX, positionY, figureValue FROM BOARD_FIELD ")
                    .execute(ResultProducers.returningResults()).map((rs, ctx) -> new BoardField(rs.getInt("positionX"), rs.getInt("positionY"), FieldValue.valueOf(rs.getString("figureValue")))).list());
        });
        return result;
    }

    public List<BoardField>  findFirstNRecords(final int n) {
        var result = new ArrayList<BoardField>();
        jdbi.useHandle(handle -> {
            result.addAll(handle.createQuery("SELECT TOP :n positionX, positionY, figureValue FROM BOARD_FIELD ORDER BY id ASC")
                    .bind("n", n)
                    .execute(ResultProducers.returningResults()).map((rs, ctx) -> new BoardField(rs.getInt("positionX"), rs.getInt("positionY"), FieldValue.valueOf(rs.getString("figureValue")))).list());
        });
        return result;
    }

    public List<BoardField>  findLastNRecords(final int n) {
        var result = new ArrayList<BoardField>();
        jdbi.useHandle(handle -> {
            result.addAll(handle.createQuery("SELECT TOP :n positionX, positionY, figureValue FROM BOARD_FIELD ORDER BY id DESC")
                    .bind("n", n)
                    .execute(ResultProducers.returningResults()).map((rs, ctx) -> new BoardField(rs.getInt("positionX"), rs.getInt("positionY"), FieldValue.valueOf(rs.getString("figureValue")))).list());
        });
        return result;
    }

    public Optional<BoardField> findByPosition(final FieldPosition fieldPosition) {
        var result = new ArrayList<BoardField>();
        jdbi.useHandle(handle -> {
            result.addAll(handle.createQuery("SELECT positionX, positionY, figureValue FROM BOARD_FIELD WHERE positionX = :positionX AND positionY = :positionY")
                    .bind("positionX", fieldPosition.getPositionX())
                    .bind("positionY", fieldPosition.getPositionY())
                    .execute(ResultProducers.returningResults())
                    .map((rs, ctx) -> new BoardField(rs.getInt("positionX"), rs.getInt("positionY"), FieldValue.valueOf(rs.getString("figureValue")))).list());
        });
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }


    public void updatePositionValue(final FieldPosition fieldPosition, final FieldValue fieldValue) {
        jdbi.useHandle(handle -> {
            handle.createUpdate("UPDATE BOARD_FIELD " +
                    "SET figureValue = :figureValue " +
                    "WHERE positionX = :positionX AND positionY = :positionY")
                    .bind("positionX", fieldPosition.getPositionX())
                    .bind("positionY", fieldPosition.getPositionY())
                    .bind("figureValue", fieldValue)
                    .execute();
        });
    }
}
