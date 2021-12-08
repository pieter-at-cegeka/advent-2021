package be.acerta.pieter.advent2021.day8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Reading {
    private final List<Digit> allDigits;
    private final Digit[] allDigitsSorted;
    private final List<Digit> displayedDigits;

    private final Map<Integer, Character> segmentMapping;

    private static final Pattern PATTERN = Pattern.compile("^(\\w+) (\\w+) (\\w+) (\\w+) (\\w+) (\\w+) (\\w+) (\\w+) (\\w+) (\\w+) \\| (\\w+) (\\w+) (\\w+) (\\w+)$");

    public Reading(String readingAsString) {
        Matcher matcher = PATTERN.matcher(readingAsString);
        matcher.matches();

        allDigits = Stream.of(
                        matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5),
                        matcher.group(6), matcher.group(7), matcher.group(8), matcher.group(9), matcher.group(10))
                .sorted(comparing(String::length))
                .map(Digit::new)
                .collect(toList());

        displayedDigits = Stream.of(
                        matcher.group(11), matcher.group(12), matcher.group(13), matcher.group(14))
                .map(Digit::new)
                .collect(toList());


        allDigitsSorted = new Digit[10];
        allDigitsSorted[1] = allDigits.get(0);
        allDigitsSorted[4] = allDigits.get(2);
        allDigitsSorted[7] = allDigits.get(1);
        allDigitsSorted[8] = allDigits.get(9);

        segmentMapping = new HashMap<>();
        int mappingOfA = deriveSegmentA();
        segmentMapping.put(mappingOfA, 'a');
        segmentMapping.put(deriveSegmentB(), 'b');
        segmentMapping.put(deriveSegmentC(mappingOfA), 'c');
        segmentMapping.put(deriveSegmentD(), 'd');
        segmentMapping.put(deriveSegmentE(), 'e');
        segmentMapping.put(deriveSegmentF(), 'f');
        segmentMapping.put(deriveSegmentG(), 'g');

        allDigits.forEach(digit -> allDigitsSorted[digit.deriveValue(segmentMapping)] = digit);
    }

    private int deriveSegmentA() {
        Digit seven = allDigitsSorted[7];
        Digit one = allDigitsSorted[1];
        HashSet<Integer> segmentAAsSet = new HashSet<>(seven.getDisplayedSegments());
        segmentAAsSet.removeAll(one.getDisplayedSegments());
        return segmentAAsSet.iterator().next();
    }

    private int deriveSegmentB() {
        return getActivationCounts().entrySet().stream()
                .filter(entry -> entry.getValue().size() == 6)
                .findFirst().orElseThrow().getKey();
    }

    private int deriveSegmentC(int mappingOfA) {
        return getActivationCounts().entrySet().stream()
                .filter(entry -> entry.getValue().size() == 8)
                .filter(entry -> !entry.getKey().equals(mappingOfA))
                .findFirst().orElseThrow().getKey();
    }

    private int deriveSegmentD() {
        Digit four = allDigitsSorted[4];
        return getActivationCounts().entrySet().stream()
                .filter(entry -> entry.getValue().size() == 7)
                .filter(entry -> four.getDisplayedSegments().contains(entry.getKey()))
                .findFirst().orElseThrow().getKey();
    }

    private int deriveSegmentE() {
        return getActivationCounts().entrySet().stream()
                .filter(entry -> entry.getValue().size() == 4)
                .findFirst().orElseThrow().getKey();
    }

    private int deriveSegmentF() {
        return getActivationCounts().entrySet().stream()
                .filter(entry -> entry.getValue().size() == 9)
                .findFirst().orElseThrow().getKey();
    }

    private int deriveSegmentG() {
        Digit four = allDigitsSorted[4];
        return getActivationCounts().entrySet().stream()
                .filter(entry -> entry.getValue().size() == 7)
                .filter(entry -> !four.getDisplayedSegments().contains(entry.getKey()))
                .findFirst().orElseThrow().getKey();
    }

    private Map<Integer, List<Integer>> getActivationCounts() {
        return allDigits.stream()
                .map(Digit::getDisplayedSegments)
                .flatMap(Set::stream)
                .collect(groupingBy(Function.identity()));
    }

    public int countNumberOfEasyDigits() {
        Digit one = allDigitsSorted[1];
        Digit four = allDigitsSorted[4];
        Digit seven = allDigitsSorted[7];
        Digit eight = allDigitsSorted[8];
        return (int) displayedDigits.stream()
                .filter(displayedDigits -> Set.of(one, four, seven, eight).contains(displayedDigits))
                .count();
    }

    public int getDisplayedValue() {
        return 1000 * valueOf(displayedDigits.get(0)) +
                100 * valueOf(displayedDigits.get(1)) +
                10 * valueOf(displayedDigits.get(2)) +
                1 * valueOf(displayedDigits.get(3));
    }

    private int valueOf(Digit digit) {
        return Arrays.asList(allDigitsSorted).indexOf(digit);
    }
}
