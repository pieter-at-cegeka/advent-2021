package be.acerta.pieter.advent2021.day13;

public class Dot {
    private final Coordinate originalCoordinate;
    private final Coordinate coordinateAfterFolding;

    public Dot(Coordinate originalCoordinate, Coordinate coordinateAfterFolding) {
        this.originalCoordinate = originalCoordinate;
        this.coordinateAfterFolding = coordinateAfterFolding;
    }

    public Coordinate getCoordinateAfterFolding() {
        return coordinateAfterFolding;
    }

    @Override
    public String toString() {
        return originalCoordinate.toString() + " -> " + coordinateAfterFolding.toString();
    }
}
