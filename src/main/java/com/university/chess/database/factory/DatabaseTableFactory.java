package com.university.chess.database.factory;

import com.university.chess.database.JdbiProvider;
import org.jdbi.v3.core.Jdbi;

public class DatabaseTableFactory {

    public static final String CREATE_TABLE_QUERY_FIELD_POSITION = "CREATE TABLE BOARD_FIELD "
            + "(id integer identity, positionX integer, positionY integer, figureValue varchar(20))";

    public static final String CREATE_TABLE_QUERY_STEP_HISTORY = "CREATE TABLE STEP_HISTORY "
            + "(id integer identity, fromPositionX integer, fromPositionY integer, toPositionX integer, toPositionY integer, figureValue varchar(20))";

    public static final String DROP_TABLE_QUERY_FIELD_POSITION = "DROP TABLE BOARD_FIELD IF EXISTS";

    public static final String DROP_TABLE_QUERY_STEP_HISTORY = "DROP TABLE STEP_HISTORY IF EXISTS";


    private final Jdbi jdbi = JdbiProvider.getInstance();

    public void createBoardFieldTableQuery() {
        create(CREATE_TABLE_QUERY_FIELD_POSITION);
    }

    public void createStepHistoryTableQuery() {
        create(CREATE_TABLE_QUERY_STEP_HISTORY);
    }

    public void dropBoardFieldTableQuery() {
        create(DROP_TABLE_QUERY_FIELD_POSITION);
    }

    public void dropStepHistoryTableQuery() {
        create(DROP_TABLE_QUERY_STEP_HISTORY);
    }

    private void create(final String tableQuery) {
        jdbi.useHandle(handle -> handle.execute(tableQuery));
    }
}
