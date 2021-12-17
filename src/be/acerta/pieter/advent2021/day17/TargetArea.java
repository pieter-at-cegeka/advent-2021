package be.acerta.pieter.advent2021.day17;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class TargetArea {
    private final int minimumX;
    private final int maximumX;
    private final int minimumY;
    private final int maximumY;

    public TargetArea(int minimumX, int maximumX, int minimumY, int maximumY) {
        this.minimumX = minimumX;
        this.maximumX = maximumX;
        this.minimumY = minimumY;
        this.maximumY = maximumY;
    }

    public List<Shot> calculateShotsThatHit() {
        List<XTrajectory> possibleXTrajectories = calculatePossibleXTrajectories();
        List<YTrajectory> possibleYTrajectories = calculatePossibleYTrajectories();

        List<Shot> shotsThatHit = new ArrayList<>();
        possibleXTrajectories.forEach(possibleXTrajectory ->
                possibleYTrajectories.forEach(possibleYTrajectory -> {
                    if (possibleXTrajectory.hitsInCombinationWith(possibleYTrajectory, this)) {
                        shotsThatHit.add(new Shot(possibleXTrajectory.getInitialXVelocity(), possibleYTrajectory.getInitialYVelocity()));
                    }
                }));

        return shotsThatHit;
    }

    private List<XTrajectory> calculatePossibleXTrajectories() {
        List<XTrajectory> possibleXTrajectories = new ArrayList<>();
        for (int initialXVelocity = 1; initialXVelocity <= maximumX; initialXVelocity++) {
            possibleXTrajectories.add(new XTrajectory(initialXVelocity, extrapolateXPositions(initialXVelocity)));
        }
        return possibleXTrajectories;
    }

    private List<YTrajectory> calculatePossibleYTrajectories() {
        List<YTrajectory> possibleYTrajectories = new ArrayList<>();
        for (int initialYVelocity = minimumY; initialYVelocity <= Math.abs(minimumY) + 1; initialYVelocity++) {
            List<Integer> visitedYPositions = extrapolateYPositions(initialYVelocity);

            possibleYTrajectories.add(new YTrajectory(initialYVelocity, visitedYPositions));
        }
        return possibleYTrajectories;
    }

    private List<Integer> extrapolateXPositions(int initialXVelocity) {
        List<Integer> xPositions = new ArrayList<>();
        int currentXPosition = 0;
        int currentXVelocity = initialXVelocity;
        while (currentXVelocity > 0) {
            currentXPosition += currentXVelocity--;
            xPositions.add(currentXPosition);
        }
        return xPositions;
    }

    private List<Integer> extrapolateYPositions(int initialYVelocity) {
        List<Integer> yPositions = new ArrayList<>();
        int currentYPosition = 0;
        int currentYVelocity = initialYVelocity;
        while (currentYPosition >= minimumY) {
            currentYPosition += currentYVelocity--;
            yPositions.add(currentYPosition);
        }
        return yPositions;
    }

    public String toString() {
        return format("[%s->%s,%s->%s]", minimumX, maximumX, minimumY, maximumY);
    }

    public boolean contains(int xPosition, Integer yPosition) {
        return minimumX <= xPosition && xPosition <= maximumX && minimumY <= yPosition && yPosition <= maximumY;
    }
}
