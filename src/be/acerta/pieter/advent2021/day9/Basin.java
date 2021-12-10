package be.acerta.pieter.advent2021.day9;

import java.util.Set;

public class Basin {
    private final Point lowPoint;
    private final Set<Point> points;

    public Basin(Point lowPoint, Set<Point> points) {
        this.lowPoint = lowPoint;
        this.points = points;
    }

    public int size() {
        return points.size();
    }
}
