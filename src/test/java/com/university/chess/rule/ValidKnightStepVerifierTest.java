package com.university.chess.rule;

import com.university.chess.model.FieldPosition;
import com.university.chess.model.FieldValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

public class ValidKnightStepVerifierTest {

    private static final boolean IS_EMPTY = true;
    private static final boolean IS_NOT_EMPTY = false;

    private static final boolean IS_VALID_STEP = true;
    private static final boolean IS_NOT_VALID_STEP = false;

    private static final boolean IS_NEXT_STEP_FOR_WHITE_KNIGHT = true;
    private static final boolean IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT = false;

    private static final boolean IS_NEXT_STEP_FOR_BLACK_KNIGHT = true;
    private static final boolean IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT = false;

    @Mock
    private EmptyPositionChecker emptyPositionCheckerMock;

    @Mock
    private NextStepChecker nextStepCheckerMock;

    private ValidKnightStepVerifier underTest;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        underTest = new ValidKnightStepVerifier(emptyPositionCheckerMock, nextStepCheckerMock);
    }

    @ParameterizedTest
    @MethodSource("cases")
    public void isValidTestWithCases( FieldPosition sourcePosition, FieldPosition targetPosition, FieldValue fieldValue, boolean isEmpty, boolean isNextStepForWhiteKnight, boolean isNextStepForBlackKnight, boolean isValidStep) {
        // Given
        given(emptyPositionCheckerMock.isEmpty(targetPosition)).willReturn(isEmpty);
        given(nextStepCheckerMock.isNextStepForWhiteKnight()).willReturn(isNextStepForWhiteKnight);
        given(nextStepCheckerMock.isNextStepForBlackKnight()).willReturn(isNextStepForBlackKnight);

        // When
        final var actual = underTest.isValidStep(sourcePosition, targetPosition, fieldValue);

        // Then
        assertThat(actual, is(isValidStep));
    }

    public static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 0), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 1), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 2), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 0), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 1), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 2), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 0), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 1), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 2), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),

                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 0), FieldValue.BLACK_KNIGHT, IS_NOT_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 1), FieldValue.BLACK_KNIGHT, IS_NOT_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 2), FieldValue.BLACK_KNIGHT, IS_NOT_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 0), FieldValue.BLACK_KNIGHT, IS_NOT_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 1), FieldValue.BLACK_KNIGHT, IS_NOT_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 2), FieldValue.BLACK_KNIGHT, IS_NOT_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 0), FieldValue.BLACK_KNIGHT, IS_NOT_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 1), FieldValue.BLACK_KNIGHT, IS_NOT_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 2), FieldValue.BLACK_KNIGHT, IS_NOT_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),

                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 0), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 1), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 2), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 0), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 1), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 2), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 0), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 1), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 2), FieldValue.BLACK_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),






                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 0), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 1), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 2), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 0), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 1), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 2), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 0), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 1), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 2), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NOT_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),

                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 0), FieldValue.WHITE_KNIGHT, IS_NOT_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 1), FieldValue.WHITE_KNIGHT, IS_NOT_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 2), FieldValue.WHITE_KNIGHT, IS_NOT_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 0), FieldValue.WHITE_KNIGHT, IS_NOT_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 1), FieldValue.WHITE_KNIGHT, IS_NOT_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 2), FieldValue.WHITE_KNIGHT, IS_NOT_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 0), FieldValue.WHITE_KNIGHT, IS_NOT_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 1), FieldValue.WHITE_KNIGHT, IS_NOT_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 2), FieldValue.WHITE_KNIGHT, IS_NOT_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),

                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 0), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 1), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(0, 2), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 0), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 1), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(1, 2), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 0), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 1), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_VALID_STEP),
                Arguments.of(new FieldPosition(0, 0), new FieldPosition(2, 2), FieldValue.WHITE_KNIGHT, IS_EMPTY, IS_NEXT_STEP_FOR_WHITE_KNIGHT, IS_NOT_NEXT_STEP_FOR_BLACK_KNIGHT, IS_NOT_VALID_STEP)
        );
    }
}
