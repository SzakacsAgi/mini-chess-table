package com.university.chess.rule;

import com.university.chess.database.FieldPositionRepository;
import com.university.chess.model.BoardField;
import com.university.chess.model.FieldPosition;
import com.university.chess.model.FieldValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

public class EmptyPositionCheckerTest {

    private final FieldPosition fieldPosition = new FieldPosition(0,0);

    @Mock
    private FieldPositionRepository repositoryMock;

    private EmptyPositionChecker underTest;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        underTest = new EmptyPositionChecker(repositoryMock);
    }

    @Test
    public void isEmptyShouldReturnTrueWhenThereIsNoKnightOnTheField() {
        // Given
        final var boardField = new BoardField(0, 0, FieldValue.EMPTY);

        given(repositoryMock.findByPosition(fieldPosition)).willReturn(Optional.of(boardField));

        // When
        final var actual = underTest.isEmpty(fieldPosition);

        // Then
        assertThat(actual, is(true));
    }

    @Test
    public void isEmptyShouldReturnFalseWhenThereIsBlackKnightOnTheField() {
        // Given
        final var boardField = new BoardField(0, 0, FieldValue.BLACK_KNIGHT);

        given(repositoryMock.findByPosition(fieldPosition)).willReturn(Optional.of(boardField));

        // When
        final var actual = underTest.isEmpty(fieldPosition);

        // Then
        assertThat(actual, is(false));
    }
}
