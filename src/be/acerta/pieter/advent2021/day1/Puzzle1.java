package be.acerta.pieter.advent2021.day1;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle1 {

    public static void main(String... args) {
        runDetection(1);
        runDetection(3);
    }

    private static void runDetection(int windowSize) {
        List<Long> depths = InputFileReadingUtil.readFileLines("puzzle1_input.txt").stream()
                .map(Long::parseLong)
                .collect(toList());

        int numberOfIncreases = 0;
        for (int i = windowSize; i < depths.size(); i++) {
            Long previousValue = depths.get(i - windowSize);
            Long currentValue = depths.get(i);

            numberOfIncreases += currentValue > previousValue ? 1 : 0;
        }

        System.out.println(format("Number of increases for window size %s: %s", windowSize, numberOfIncreases));
    }
}
