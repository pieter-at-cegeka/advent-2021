package be.acerta.pieter.advent2021.day2;

public enum Direction {
    FORWARD,
    UP,
    DOWN;

    public static Direction fromString(String directionAsString) {
        return valueOf(directionAsString.toUpperCase());
    }
}
