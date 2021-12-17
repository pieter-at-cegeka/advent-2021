package be.acerta.pieter.advent2021.day17;

import java.util.Collection;
import java.util.List;

public class YTrajectory {
    private final int initialYVelocity;
    private final List<Integer> visitedYPositions;

    public YTrajectory(int initialYVelocity, List<Integer> visitedYPositions) {
        this.initialYVelocity = initialYVelocity;
        this.visitedYPositions = visitedYPositions;
    }

    public int getInitialYVelocity() {
        return initialYVelocity;
    }

    public List<Integer> getVisitedYPositions() {
        return visitedYPositions;
    }
}
