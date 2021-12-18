package be.acerta.pieter.advent2021.day18;

public class HumanNumber implements SnailFishNumberElement {
    private final int value;

    public HumanNumber(String humanNumberAsString) {
        this.value = Integer.parseInt(humanNumberAsString);
    }

    @Override
    public int calculateMagnitude() {
        return value;
    }
}
