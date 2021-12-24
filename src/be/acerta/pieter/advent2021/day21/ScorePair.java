package be.acerta.pieter.advent2021.day21;

import java.util.Objects;

public class ScorePair {
    private final int scoreOfPlayer1;
    private final int scoreOfPlayer2;

    public ScorePair(int scoreOfPlayer1, int scoreOfPlayer2) {
        this.scoreOfPlayer1 = scoreOfPlayer1;
        this.scoreOfPlayer2 = scoreOfPlayer2;
    }

    public int getScoreOfPlayer1() {
        return scoreOfPlayer1;
    }

    public int getScoreOfPlayer2() {
        return scoreOfPlayer2;
    }

    public boolean isNonFinalScore() {
        return scoreOfPlayer1 < 21 && scoreOfPlayer2 < 21;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScorePair scorePair = (ScorePair) o;
        return scoreOfPlayer1 == scorePair.scoreOfPlayer1 && scoreOfPlayer2 == scorePair.scoreOfPlayer2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreOfPlayer1, scoreOfPlayer2);
    }

    @Override
    public String toString() {
        return "(" + scoreOfPlayer1 + "," + scoreOfPlayer2 + ")";
    }
}
