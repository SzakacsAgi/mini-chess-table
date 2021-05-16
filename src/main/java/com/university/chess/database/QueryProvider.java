package com.university.chess.database;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class QueryProvider {

    public static final String SELECT_ALL_PROJECT_QUERY = "SELECT * FROM BOARD_FIELD";

}
