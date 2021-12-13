package be.acerta.pieter.advent2021.day13;

public enum Direction {
    HORIZONTAL,
    VERTICAL;

    public static Direction fromAxisName(String axisName) {
        switch (axisName) {
            case "x": return VERTICAL;
            case "y": return HORIZONTAL;
        }

        throw new IllegalArgumentException(axisName);
    }
}
