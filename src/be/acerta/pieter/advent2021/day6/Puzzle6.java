package be.acerta.pieter.advent2021.day6;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class Puzzle6 {
    public static void main(String... args) {
        String lanternFishTimerValuesAsString = InputFileReadingUtil.readFileLines("puzzle6_input.txt").get(0);

        Map<Integer, Long> numberOfLanternFishesByTimerValue = Stream.of(lanternFishTimerValuesAsString.split(","))
                .map(Integer::parseInt)
                .collect(groupingBy(Function.identity()))
                .entrySet().stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        entry -> (long) entry.getValue().size()
                ));

        for (int i = 0; i < 80; i++) {
            numberOfLanternFishesByTimerValue = progressOneDay(numberOfLanternFishesByTimerValue);
        }

        long numberOfLanternFishesAfter80Days = numberOfLanternFishesByTimerValue.values().stream().reduce(0l, Long::sum);

        for (int i = 80; i < 256; i++) {
            numberOfLanternFishesByTimerValue = progressOneDay(numberOfLanternFishesByTimerValue);
        }

        long numberOfLanternFishesAfter256Days = numberOfLanternFishesByTimerValue.values().stream().reduce(0l, Long::sum);

        System.out.println(format("Number of fishes after 80 days is %s, after 256 is %s", numberOfLanternFishesAfter80Days, numberOfLanternFishesAfter256Days));
    }

    private static Map<Integer, Long> progressOneDay(Map<Integer, Long> numberOfLanternFishesByTimerValue) {
        Map<Integer, Long> result = new HashMap<>();
        numberOfLanternFishesByTimerValue.forEach((timerValue, numberOfLanternFishes) -> {
            if (timerValue == 0) {
                result.put(8, result.getOrDefault(8, 0l) + numberOfLanternFishes);
                result.put(6, result.getOrDefault(6, 0l) + numberOfLanternFishes);
            } else {
                result.put(timerValue - 1, result.getOrDefault(timerValue - 1, 0l) + numberOfLanternFishes);
            }
        });
        return result;
    }
}
