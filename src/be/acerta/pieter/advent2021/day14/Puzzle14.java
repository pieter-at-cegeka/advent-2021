package be.acerta.pieter.advent2021.day14;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle14 {
    public static void main(String... args) {
        List<String> inputLines = InputFileReadingUtil.readFileLines("puzzle14_input.txt");

        Polymer polymer = new Polymer(inputLines.get(0));
        List<PairInsertionRule> pairInsertionRules = inputLines.stream().skip(2)
                .map(PairInsertionRule::new)
                .collect(toList());

        PolymerCharacterOccurrencePredictor polymerCharacterOccurrencePredictor = new PolymerCharacterOccurrencePredictor(pairInsertionRules);

        Map<Character, Long> occurrencesPerCharacterAfter10Iterations = polymerCharacterOccurrencePredictor.calculateOccurrencesPerCharacterAfterIterations(polymer, 10);

        long maximumOccurrence = occurrencesPerCharacterAfter10Iterations.values().stream()
                .mapToLong(value -> value)
                .max().orElseThrow();
        long minimumOccurrence = occurrencesPerCharacterAfter10Iterations.values().stream()
                .mapToLong(value -> value)
                .min().orElseThrow();
        long occurrenceDifferenceAfter10Iterations = maximumOccurrence - minimumOccurrence;

        Map<Character, Long> occurrencesPerCharacterAfter40Iterations = polymerCharacterOccurrencePredictor.calculateOccurrencesPerCharacterAfterIterations(polymer, 40);

        maximumOccurrence = occurrencesPerCharacterAfter40Iterations.values().stream()
                .mapToLong(value -> value)
                .max().orElseThrow();
        minimumOccurrence = occurrencesPerCharacterAfter40Iterations.values().stream()
                .mapToLong(value -> value)
                .min().orElseThrow();
        long occurrenceDifferenceAfter40Iterations = maximumOccurrence - minimumOccurrence;

        System.out.println(format("The difference between the maximum and the minimum occurrence after 10 iterations is %s, and after 40 is %s",
                occurrenceDifferenceAfter10Iterations,
                occurrenceDifferenceAfter40Iterations));
    }
}
