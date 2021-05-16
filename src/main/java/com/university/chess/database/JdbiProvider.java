package com.university.chess.database;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import java.util.Properties;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class JdbiProvider {

    private final static Jdbi INSTANCE = init();

    public static Jdbi init() {
        Properties properties = new Properties();
        properties.setProperty("username", "sa");
        properties.setProperty("password", "");
        return Jdbi.create("jdbc:hsqldb:mem:testDB", properties);
    }

    public static Jdbi getInstance() {
        return INSTANCE;
    }
}
