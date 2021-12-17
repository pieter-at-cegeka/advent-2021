package be.acerta.pieter.advent2021.day17;

import java.util.List;

public class XTrajectory {
    private final int initialXVelocity;
    private final List<Integer> visitedXPositions;

    public XTrajectory(int initialXVelocity, List<Integer> visitedXPositions) {
        this.initialXVelocity = initialXVelocity;
        this.visitedXPositions = visitedXPositions;
    }

    public int getInitialXVelocity() {
        return initialXVelocity;
    }

    public boolean hitsInCombinationWith(YTrajectory yTrajectory, TargetArea targetArea) {
        List<Integer> visitedYPositions = yTrajectory.getVisitedYPositions();
        for (int step = 0; step < visitedYPositions.size(); step++) {
            if (targetArea.contains(getXPositionAtStep(step), visitedYPositions.get(step))) {
                return true;
            }
        }
        return false;
    }

    private int getXPositionAtStep(int step) {
        return visitedXPositions.get(Math.min(step, visitedXPositions.size() - 1));
    }
}
