package be.acerta.pieter.advent2021.day2;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle2A {

    public static void main(String... args) {
        List<Command> commands = InputFileReadingUtil.readFileLines("puzzle2_testinput.txt").stream()
                .map(Command::fromString)
                .collect(toList());

        long positionOnXAxis = commands.stream()
                .mapToLong(Command::getImpactOnXAxis)
                .sum();

        long positionOnZAxis = commands.stream()
                .mapToLong(Command::getImpactOnZAxis)
                .sum();

        long depth = positionOnZAxis * -1l;
        long solution = positionOnXAxis * depth;

        System.out.println(format("Position on x axis: %s, position on z axis: %s, depth: %s, solution: %s",
                positionOnXAxis,
                positionOnZAxis,
                depth,
                solution));
    }
}
