package be.acerta.pieter.advent2021.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementPair {
    private final char firstElement;
    private final char secondElement;

    private ElementPair(char firstElement, char secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public char getFirstElement() {
        return firstElement;
    }

    public char getSecondElement() {
        return secondElement;
    }

    private static final Map<List<Character>, ElementPair> KNOWN_PAIRS = new HashMap<>();

    public static ElementPair of(char firstElement, char secondElement) {
        List<Character> key = List.of(firstElement, secondElement);
        if (!KNOWN_PAIRS.containsKey(key)) {
            KNOWN_PAIRS.put(key, new ElementPair(firstElement, secondElement));
        }

        return KNOWN_PAIRS.get(key);
    }

    @Override
    public String toString() {
        return "" + firstElement + secondElement;
    }
}
