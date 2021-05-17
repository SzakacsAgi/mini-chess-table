package com.university.chess.rule.impl;

import com.university.chess.model.FieldPosition;
import com.university.chess.rule.KnightStepRule;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class KnightStepRuleFromEightPositionTest {

    private final KnightStepRule underTest = new KnightStepRuleFromEighthPosition();

    @ParameterizedTest
    @MethodSource("positiveCases")
    public void isValidTestWithPositiveCases(final FieldPosition fieldPosition) {
        // Given

        // When
        final var actual = underTest.isValid(fieldPosition);

        // Then
        assertThat(actual, is(true));
    }

    @ParameterizedTest
    @MethodSource("negativeCases")
    public void isValidTestWithNegativeCases(final FieldPosition fieldPosition) {
        // Given

        // When
        final var actual = underTest.isValid(fieldPosition);

        // Then
        assertThat(actual, is(false));
    }

    private static Stream<FieldPosition> positiveCases() {
        return Stream.of(new FieldPosition(0,0), new FieldPosition(0, 2));
    }

    private static Stream<FieldPosition> negativeCases() {
        return Stream.of(
                new FieldPosition(0, 1),
                new FieldPosition(1,0),
                new FieldPosition(1, 1),
                new FieldPosition(1,2),
                new FieldPosition(2, 0),
                new FieldPosition(2, 1),
                new FieldPosition(2,2)
        );
    }
}
