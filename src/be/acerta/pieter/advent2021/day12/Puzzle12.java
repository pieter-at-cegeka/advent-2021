package be.acerta.pieter.advent2021.day12;

import java.util.List;

import static be.acerta.pieter.advent2021.InputFileReadingUtil.readFileLines;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle12 {
    public static void main(String... args) {
        List<Connection> connections = readFileLines("puzzle12_input.txt").stream()
                .map(Connection::new)
                .collect(toList());
        Cave start = connections.stream()
                .map(Connection::getCaves)
                .flatMap(List::stream)
                .filter(Cave::isStart)
                .findFirst()
                .orElseThrow();

        List<Path> allPathsThatVisitSmallCavesOnlyOnce = Path.findAllContinuations(List.of(start), false);
        List<Path> allPathsThatVisitAtMostOneSmallCaveTwice = Path.findAllContinuations(List.of(start), true);

        int amountOfPathsThatVisitSmallCavesOnlyOnce = allPathsThatVisitSmallCavesOnlyOnce.size();
        int amountOfPathsThatVisitAtMostOneSmallCavesTwice = allPathsThatVisitAtMostOneSmallCaveTwice.size();

        System.out.println(format("Amount of paths that visit small caves at most once is %s, amount of paths that visit at most once small cave twice is %s",
                amountOfPathsThatVisitSmallCavesOnlyOnce,
                amountOfPathsThatVisitAtMostOneSmallCavesTwice));
    }
}
