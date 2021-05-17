package com.university.chess.rule;

import com.university.chess.database.StepHistoryRepository;
import com.university.chess.model.FieldValue;
import com.university.chess.model.StepHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

public class NextStepCheckerTest {

    private final StepHistory blackNightStep = new StepHistory(0,0,1,1, FieldValue.BLACK_KNIGHT);
    private final StepHistory whiteNightStep = new StepHistory(0,0,1,1, FieldValue.WHITE_KNIGHT);

    @Mock
    private StepHistoryRepository repositoryMock;

    private NextStepChecker underTest;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        underTest = new NextStepChecker(repositoryMock);
    }

    @Test
    public void isNextStepForWhiteKnightShouldReturnTrueWhenThereIsNoStepYet() {
        // Given
        given(repositoryMock.findByLastStep()).willReturn(Optional.empty());

        // When
        final var actual = underTest.isNextStepForWhiteKnight();

        // Then
        assertThat(actual, is(true));
    }

    @Test
    public void isNextStepForWhiteKnightShouldReturnTrueWhenTheBlackKnightDidThePreviousStep() {
        // Given
        given(repositoryMock.findByLastStep()).willReturn(Optional.of(blackNightStep));

        // When
        final var actual = underTest.isNextStepForWhiteKnight();

        // Then
        assertThat(actual, is(true));
    }

    @Test
    public void isNextStepForWhiteKnightShouldReturnFalseWhenTheWhiteKnightDidThePreviousStep() {
        // Given
        given(repositoryMock.findByLastStep()).willReturn(Optional.of(whiteNightStep));

        // When
        final var actual = underTest.isNextStepForWhiteKnight();

        // Then
        assertThat(actual, is(false));
    }

    @Test
    public void isNextStepForBlackKnightShouldReturnFalseWhenThereIsNoStepYet() {
        // Given
        given(repositoryMock.findByLastStep()).willReturn(Optional.empty());

        // When
        final var actual = underTest.isNextStepForBlackKnight();

        // Then
        assertThat(actual, is(false));
    }

    @Test
    public void isNextStepForBlackKnightShouldReturnFalseWhenTheBlackKnightDidThePreviousStep() {
        // Given
        given(repositoryMock.findByLastStep()).willReturn(Optional.of(blackNightStep));

        // When
        final var actual = underTest.isNextStepForBlackKnight();

        // Then
        assertThat(actual, is(false));
    }

    @Test
    public void isNextStepForBlackKnightShouldReturnTrueWhenTheWhiteKnightDidThePreviousStep() {
        // Given
        given(repositoryMock.findByLastStep()).willReturn(Optional.of(whiteNightStep));

        // When
        final var actual = underTest.isNextStepForBlackKnight();

        // Then
        assertThat(actual, is(true));
    }
}
