package be.acerta.pieter.advent2021.day21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Comparator.comparing;

public class Puzzle21B {
    public static void main(String... args) {
        runWithStartingPositions(4, 8);
        runWithStartingPositions(7, 6);
    }

    private static void runWithStartingPositions(int startingPositionOfPlayer1, int startingPositionOfPlayer2) {
        Map<ScorePair, List<Universe>> universesByScorePair = new HashMap<>();
        universesByScorePair.put(new ScorePair(0, 0), List.of(new Universe(true, startingPositionOfPlayer1, 0, startingPositionOfPlayer2, 0, 1)));

        boolean expandedAUniverse;
        do {
            expandedAUniverse = findExpandableUniverseAndExpandIt(universesByScorePair);
        } while (expandedAUniverse);

        long numberOfUniversesWherePlayer1Wins = universesByScorePair.entrySet().stream()
                .filter(entry -> entry.getKey().getScoreOfPlayer1() >= 21)
                .flatMap(entry -> entry.getValue().stream())
                .mapToLong(Universe::getVersionsOfThisUniverse)
                .sum();
        long numberOfUniversesWherePlayer2Wins = universesByScorePair.entrySet().stream()
                .filter(entry -> entry.getKey().getScoreOfPlayer2() >= 21)
                .flatMap(entry -> entry.getValue().stream())
                .mapToLong(Universe::getVersionsOfThisUniverse)
                .sum();

        System.out.println(format("With starting positions %s and %s, player 1 wins in %s universes, player 2 in %s.",
                startingPositionOfPlayer1,
                startingPositionOfPlayer2,
                numberOfUniversesWherePlayer1Wins,
                numberOfUniversesWherePlayer2Wins));
    }

    private static boolean findExpandableUniverseAndExpandIt(Map<ScorePair, List<Universe>> universesByScorePair) {
        Optional<ScorePair> scorePairOfUnreachableExpandableUniverses = universesByScorePair.keySet().stream()
                .filter(ScorePair::isNonFinalScore)
                .min(comparing(ScorePair::getScoreOfPlayer1).thenComparing(ScorePair::getScoreOfPlayer2));

        if (scorePairOfUnreachableExpandableUniverses.isEmpty()) {
            return false;
        } else {
            List<Universe> universesToExpand = universesByScorePair.get(scorePairOfUnreachableExpandableUniverses.get());

            for (Universe universeToExpand : universesToExpand) {
                Map<ScorePair, Universe> expansion = universeToExpand.expand();
                mergeExpansionIntoAllUniverses(universesByScorePair, expansion);
            }

            universesByScorePair.remove(scorePairOfUnreachableExpandableUniverses.get());

            return true;
        }
    }

    private static void mergeExpansionIntoAllUniverses(Map<ScorePair, List<Universe>> universesByScorePair, Map<ScorePair, Universe> expansion) {
        expansion.forEach((scorePair, expandedUniverse) -> {
            if (!universesByScorePair.containsKey(scorePair)) {
                universesByScorePair.put(scorePair, new ArrayList<>());
            }

            List<Universe> universes = universesByScorePair.get(scorePair);
            Optional<Universe> matchingUniverse = universes.stream()
                    .filter(expandedUniverse::boardMatches)
                    .findFirst();

            if (matchingUniverse.isEmpty()) {
                universes.add(expandedUniverse);
            } else {
                matchingUniverse.get().merge(expandedUniverse);
            }
        });
    }
}
