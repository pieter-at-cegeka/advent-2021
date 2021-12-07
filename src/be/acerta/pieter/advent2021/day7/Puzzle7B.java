package be.acerta.pieter.advent2021.day7;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;

public class Puzzle7B {
    public static void main(String... args) {
        List<Integer> crabPositions = Stream.of(InputFileReadingUtil.readFileLines("puzzle7_input.txt").get(0).split(","))
                .map(Integer::parseInt)
                .collect(toList());

        int maximumPosition = crabPositions.stream().max(naturalOrder()).get();

        int bestPosition = 0;
        int bestFuelUse = Integer.MAX_VALUE;
        for (int position = 0; position <= maximumPosition; position++) {
            int usablePosition = position;
            int fuelUse = crabPositions.stream()
                    .mapToInt(crabPosition -> fuelUseOf(usablePosition, crabPosition))
                    .sum();

            if (fuelUse < bestFuelUse) {
                bestFuelUse = fuelUse;
                bestPosition = position;
            } else {
                break;
            }
        }

        System.out.println(format("Best position is %s with a fuel use of %s", bestPosition, bestFuelUse));
    }

    private static int fuelUseOf(int usablePosition, int crabPosition) {
        int distance = Math.abs(usablePosition - crabPosition);

        return (distance * (1 + distance)) / 2;
    }
}
