package be.acerta.pieter.advent2021.day2;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle2B {

    public static void main(String... args) {
        List<Command> commands = InputFileReadingUtil.readFileLines("puzzle2_input.txt").stream()
                .map(Command::fromString)
                .collect(toList());

        Position position = new Position();
        commands.forEach(position::apply);

        long solution = position.getPositionAlongXAxis() * position.getDepth();

        System.out.println(format("Position on x axis: %s, depth: %s, solution: %s",
                position.getPositionAlongXAxis(),
                position.getDepth(),
                solution));
    }
}
