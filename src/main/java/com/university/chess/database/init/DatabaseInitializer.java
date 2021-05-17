package com.university.chess.database.init;

import com.university.chess.database.factory.DatabaseTableFactory;
import com.university.chess.database.populator.BoardFieldPopulator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class DatabaseInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializer.class);

    private final DatabaseTableFactory factory;
    private final BoardFieldPopulator populator;

    public void init() {
        dropTables();
        createTables();
        populateTables();
    }

    private void dropTables() {
        LOGGER.info("Dropping tables...");
        factory.dropBoardFieldTableQuery();
        factory.dropStepHistoryTableQuery();
    }

    private void createTables() {
        LOGGER.info("Creating tables...");
        factory.createBoardFieldTableQuery();
        factory.createStepHistoryTableQuery();
    }

    private void populateTables() {
        LOGGER.info("Populating tables...");
        populator.populateDatabase();
    }
}
