package be.acerta.pieter.advent2021.day18;

import static java.lang.Character.isDigit;

public interface SnailFishNumberElement {
    int calculateMagnitude();

    static SnailFishNumberElement of(String snailFishNumberElementAsString) {
        if (isDigit(snailFishNumberElementAsString.charAt(0))) {
            return new HumanNumber(snailFishNumberElementAsString);
        } else {
            return new SnailFishNumber(snailFishNumberElementAsString);
        }
    }
}
