package be.acerta.pieter.advent2021.day8;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class Digit {
    private final Set<Integer> displayedSegments;

    public Digit(String displayedSegmentsAsStrings) {
        displayedSegments = new HashSet<>();
        if (displayedSegmentsAsStrings.contains("a")) displayedSegments.add(1);
        if (displayedSegmentsAsStrings.contains("b")) displayedSegments.add(2);
        if (displayedSegmentsAsStrings.contains("c")) displayedSegments.add(3);
        if (displayedSegmentsAsStrings.contains("d")) displayedSegments.add(4);
        if (displayedSegmentsAsStrings.contains("e")) displayedSegments.add(5);
        if (displayedSegmentsAsStrings.contains("f")) displayedSegments.add(6);
        if (displayedSegmentsAsStrings.contains("g")) displayedSegments.add(7);
    }

    public Set<Integer> getDisplayedSegments() {
        return displayedSegments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Digit digit = (Digit) o;
        return Objects.equals(displayedSegments, digit.displayedSegments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayedSegments);
    }

    public int deriveValue(Map<Integer, Character> segmentMapping) {
        Set<Character> displayedSegmentsAsCharacters = displayedSegments.stream()
                .map(segmentMapping::get)
                .collect(toSet());

        if (displayedSegmentsAsCharacters.equals(Set.of('a', 'b', 'c', 'e', 'f', 'g'))) {
            return 0;
        } else if (displayedSegmentsAsCharacters.equals(Set.of('c', 'f'))) {
            return 1;
        } else if (displayedSegmentsAsCharacters.equals(Set.of('a', 'c', 'd', 'e', 'g'))) {
            return 2;
        } else if (displayedSegmentsAsCharacters.equals(Set.of('a', 'c', 'd', 'f', 'g'))) {
            return 3;
        } else if (displayedSegmentsAsCharacters.equals(Set.of('b', 'c', 'd', 'f'))) {
            return 4;
        } else if (displayedSegmentsAsCharacters.equals(Set.of('a', 'b', 'd', 'f', 'g'))) {
            return 5;
        } else if (displayedSegmentsAsCharacters.equals(Set.of('a', 'b', 'd', 'e', 'f', 'g'))) {
            return 6;
        } else if (displayedSegmentsAsCharacters.equals(Set.of('a', 'c', 'f'))) {
            return 7;
        } else if (displayedSegmentsAsCharacters.equals(Set.of('a', 'b', 'c', 'd', 'e', 'f', 'g'))) {
            return 8;
        } else if (displayedSegmentsAsCharacters.equals(Set.of('a', 'b', 'c', 'd', 'f', 'g'))) {
            return 9;
        } else

        throw new IllegalStateException();
    }
}
