package com.university.chess.database;

import org.jdbi.v3.core.Jdbi;

import java.util.Properties;

public final class DatabaseProvider {

    public static Jdbi init() {
        Properties properties = new Properties();
        properties.setProperty("username", "sa");
        properties.setProperty("password", "");
        return Jdbi.create("jdbc:hsqldb:mem:testDB", properties);
    }
}
