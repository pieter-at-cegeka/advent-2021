package be.acerta.pieter.advent2021.day5;

import static java.lang.String.format;

public class Point {
    private final int xCoordinate;
    private final int yCoordinate;

    public Point(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public int distanceSquared(Point other) {
        return (this.getXCoordinate() - other.getXCoordinate()) * (this.getXCoordinate() - other.getXCoordinate())
                + (this.getYCoordinate() - other.getYCoordinate()) * (this.getYCoordinate() - other.getYCoordinate());
    }

    @Override
    public String toString() {
        return format("(%s,%s)", xCoordinate, yCoordinate);
    }
}
