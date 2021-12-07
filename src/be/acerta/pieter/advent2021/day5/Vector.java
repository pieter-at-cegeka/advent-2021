package be.acerta.pieter.advent2021.day5;

import static java.lang.String.format;

public class Vector {
    private final Point start;
    private final Point end;

    private final int xDifferenceFromEndToStart;
    private final int yDifferenceFromEndToStart;

    private final int minXCoordinate;
    private final int maxXCoordinate;
    private final int minYCoordinate;
    private final int maxYCoordinate;

    public Vector(Point start, Point end) {
        this.start = start;
        this.end = end;

        this.xDifferenceFromEndToStart = end.getXCoordinate() - start.getXCoordinate();
        this.yDifferenceFromEndToStart = end.getYCoordinate() - start.getYCoordinate();

        this.minXCoordinate = Math.min(start.getXCoordinate(), end.getXCoordinate());
        this.maxXCoordinate = Math.max(start.getXCoordinate(), end.getXCoordinate());
        this.minYCoordinate = Math.min(start.getYCoordinate(), end.getYCoordinate());
        this.maxYCoordinate = Math.max(start.getYCoordinate(), end.getYCoordinate());
    }

    public boolean contains(Point point) {
        int xDifferenceFromPointToStart = point.getXCoordinate() - start.getXCoordinate();
        int yDifferenceFromPointToStart = point.getYCoordinate() - start.getYCoordinate();

        int crossProductOfVectors = xDifferenceFromPointToStart * yDifferenceFromEndToStart - yDifferenceFromPointToStart * xDifferenceFromEndToStart;

        boolean pointLiesOnLineFromStartToEnd = crossProductOfVectors == 0;
        if (pointLiesOnLineFromStartToEnd) {
            boolean pointLiesBetweenStartAndEnd =
                    minXCoordinate <= point.getXCoordinate() && point.getXCoordinate() <= maxXCoordinate &&
                    minYCoordinate <= point.getYCoordinate() && point.getYCoordinate() <= maxYCoordinate;

            return pointLiesBetweenStartAndEnd;
        }

        return false;
    }

    @Override
    public String toString() {
        return format("%s -> %s", start, end);
    }
}
