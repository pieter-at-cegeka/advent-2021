package be.acerta.pieter.advent2021.day21;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class Universe {

    private final boolean playerOneMovesNext;

    private final int positionOfPlayer1;
    private final int scoreOfPlayer1;

    private final int positionOfPlayer2;
    private final int scoreOfPlayer2;

    private long versionsOfThisUniverse;

    public Universe(boolean playerOneMovesNext, int positionOfPlayer1, int scoreOfPlayer1, int positionOfPlayer2, int scoreOfPlayer2, long versionsOfThisUniverse) {
        this.playerOneMovesNext = playerOneMovesNext;
        this.positionOfPlayer1 = positionOfPlayer1;
        this.scoreOfPlayer1 = scoreOfPlayer1;
        this.positionOfPlayer2 = positionOfPlayer2;
        this.scoreOfPlayer2 = scoreOfPlayer2;
        this.versionsOfThisUniverse = versionsOfThisUniverse;
    }

    public int getScoreOfPlayer1() {
        return scoreOfPlayer1;
    }

    public int getScoreOfPlayer2() {
        return scoreOfPlayer2;
    }

    public long getVersionsOfThisUniverse() {
        return versionsOfThisUniverse;
    }

    public Map<ScorePair, Universe> expand() {
        return Stream.of(expandWithRollSumAndOccurrence(3, 1),
                        expandWithRollSumAndOccurrence(4, 3),
                        expandWithRollSumAndOccurrence(5, 6),
                        expandWithRollSumAndOccurrence(6, 7),
                        expandWithRollSumAndOccurrence(7, 6),
                        expandWithRollSumAndOccurrence(8, 3),
                        expandWithRollSumAndOccurrence(9, 1))
                .collect(toMap(
                        universe -> new ScorePair(universe.getScoreOfPlayer1(), universe.getScoreOfPlayer2()),
                        Function.identity()));
    }

    private Universe expandWithRollSumAndOccurrence(int rollSum, int occurrence) {
        if (playerOneMovesNext) {
            int newPositionOfPlayer1 = move(positionOfPlayer1, rollSum);
            return new Universe(false, newPositionOfPlayer1, scoreOfPlayer1 + newPositionOfPlayer1, positionOfPlayer2, scoreOfPlayer2, versionsOfThisUniverse * occurrence);
        } else {
            int newPositionOfPlayer2 = move(positionOfPlayer2, rollSum);
            return new Universe(true, positionOfPlayer1, scoreOfPlayer1, newPositionOfPlayer2, scoreOfPlayer2 + newPositionOfPlayer2, versionsOfThisUniverse * occurrence);
        }
    }

    private static int move(int position, int moves) {
        int result = position + moves;
        while (result > 10) {
            result -= 10;
        }
        return result;
    }

    public boolean boardMatches(Universe other) {
        return this.playerOneMovesNext == other.playerOneMovesNext
                && this.positionOfPlayer1 == other.positionOfPlayer1
                && this.positionOfPlayer2 == other.positionOfPlayer2;
    }

    public void merge(Universe other) {
        this.versionsOfThisUniverse += other.versionsOfThisUniverse;
    }
}
