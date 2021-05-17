package com.university.chess.rule.com.university.chess.rule;

import com.university.chess.database.FieldPositionRepository;
import com.university.chess.model.BoardField;
import com.university.chess.model.FieldPosition;
import com.university.chess.model.FieldValue;
import com.university.chess.rule.EndGameChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

public class EndGameCheckerTest {

    @Mock
    private FieldPositionRepository repositoryMock;

    private EndGameChecker underTest;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        underTest = new EndGameChecker(repositoryMock);
    }

    @ParameterizedTest
    @MethodSource("cases")
    public void isEndGameTestWithCases(FieldValue first, FieldValue second, FieldValue third, FieldValue fourth, FieldValue fifth, FieldValue sixth, boolean isEndGame) {
        // Given
        given(repositoryMock.findFirstNRecords(3)).willReturn(List.of(create(first), create(second), create(third)));
        given(repositoryMock.findLastNRecords(3)).willReturn(List.of(create(fourth), create(fifth), create(sixth)));

        // When
        final var actual = underTest.isEndGame();

        // Then
        assertThat(actual, is(isEndGame));
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(FieldValue.BLACK_KNIGHT, FieldValue.BLACK_KNIGHT, FieldValue.BLACK_KNIGHT, FieldValue.WHITE_KNIGHT, FieldValue.WHITE_KNIGHT, FieldValue.WHITE_KNIGHT, true),
                Arguments.of(FieldValue.EMPTY, FieldValue.BLACK_KNIGHT, FieldValue.BLACK_KNIGHT, FieldValue.WHITE_KNIGHT, FieldValue.WHITE_KNIGHT, FieldValue.WHITE_KNIGHT, false),
                Arguments.of(FieldValue.BLACK_KNIGHT, FieldValue.BLACK_KNIGHT, FieldValue.BLACK_KNIGHT, FieldValue.WHITE_KNIGHT, FieldValue.EMPTY, FieldValue.WHITE_KNIGHT, false)
        );
    }

    private BoardField create(final FieldValue fieldValue) {
        return new BoardField(0,0, fieldValue);
    }
}
