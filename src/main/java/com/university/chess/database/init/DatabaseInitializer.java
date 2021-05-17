package com.university.chess.database.init;

import com.university.chess.database.factory.DatabaseTableFactory;
import com.university.chess.database.populator.BoardFieldPopulator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DatabaseInitializer {

    private final DatabaseTableFactory factory;
    private final BoardFieldPopulator populator;

    public void init() {
        dropTables();
        createTables();
        populateTables();
    }

    private void dropTables() {
        factory.dropBoardFieldTableQuery();
        factory.dropStepHistoryTableQuery();
    }

    private void createTables() {
        factory.createBoardFieldTableQuery();
        factory.createStepHistoryTableQuery();
    }

    private void populateTables() {
        populator.populateDatabase();
    }
}
