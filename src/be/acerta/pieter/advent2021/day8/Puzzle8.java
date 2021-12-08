package be.acerta.pieter.advent2021.day8;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle8 {
    public static void main(String... args) {
        List<Reading> readings = InputFileReadingUtil.readFileLines("puzzle8_input.txt").stream()
                .map(Reading::new)
                .collect(toList());

        int numberOfEasyDigits = readings.stream()
                .mapToInt(Reading::countNumberOfEasyDigits)
                .sum();

        int sumOfDisplayedValues = readings.stream()
                        .mapToInt(Reading::getDisplayedValue)
                                .sum();

        System.out.println(format("The number of easy digits is %s, the sum of displayedValues is %s",
                numberOfEasyDigits,
                sumOfDisplayedValues));
    }

}
