package be.acerta.pieter.advent2021.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Path {
    private final List<Cave> caves;

    public Path(List<Cave> caves) {
        this.caves = caves;
    }

    public static List<Path> findAllContinuations(List<Cave> visitedCaves, boolean atMostOneSmallCaveCanBeVisitedTwice) {
        Cave currentEndPoint = visitedCaves.get(visitedCaves.size() - 1);
        if (currentEndPoint.isEnd()) {
            return List.of(new Path(visitedCaves));
        } else {
            return currentEndPoint.findNeighbours().stream()
                    .filter(neighbour1 -> isLegalContinuation(visitedCaves, neighbour1, atMostOneSmallCaveCanBeVisitedTwice))
                    .map(neighbour -> {
                        List<Cave> continuation = appendToCopy(visitedCaves, neighbour);
                        return findAllContinuations(continuation, atMostOneSmallCaveCanBeVisitedTwice);
                    })
                    .flatMap(List::stream)
                    .collect(toList());
        }
    }

    private static boolean isLegalContinuation(List<Cave> visitedCaves, Cave cave, boolean atMostOneSmallCaveCanBeVisitedTwice) {
        return !cave.isStart()
                && (!cave.isSmall() || smallCaveCanBeVisited(visitedCaves, cave, atMostOneSmallCaveCanBeVisitedTwice));
    }

    private static boolean smallCaveCanBeVisited(List<Cave> visitedCaves, Cave cave, boolean atMostOneSmallCaveCanBeVisitedTwice) {
        return !visitedCaves.contains(cave) || (atMostOneSmallCaveCanBeVisitedTwice && noSmallCaveHasBeenVisitedTwice(visitedCaves));
    }

    private static boolean noSmallCaveHasBeenVisitedTwice(List<Cave> visitedCaves) {
        return visitedCaves.stream()
                .filter(Cave::isSmall)
                .collect(groupingBy(Function.identity()))
                .values().stream()
                .noneMatch(occurrenceOfSmallCave -> occurrenceOfSmallCave.size() == 2);
    }

    private static List<Cave> appendToCopy(List<Cave> caves, Cave cave) {
        List<Cave> result = new ArrayList<>(caves);
        result.add(cave);
        return result;
    }
}
