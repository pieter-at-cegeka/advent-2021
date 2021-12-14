package be.acerta.pieter.advent2021.day14;

import java.util.ArrayList;
import java.util.List;

public class Polymer {
    private final String polymerTemplate;

    public Polymer(String polymerTemplate) {
        this.polymerTemplate = polymerTemplate;
    }

    public List<ElementPair> asElementPairs() {
        List<ElementPair> elementPairs = new ArrayList<>();
        for (int index = 0; index < polymerTemplate.length() - 1; index++) {
            elementPairs.add(ElementPair.of(polymerTemplate.charAt(index), polymerTemplate.charAt(index + 1)));
        }
        return elementPairs;
    }
}
