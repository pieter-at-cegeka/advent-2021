package be.acerta.pieter.advent2021.day15;

import java.util.List;

import static java.util.Comparator.comparing;

public class ChitonCavePoint {
    private final int row;
    private final int column;
    private final int riskLevel;

    private List<ChitonCavePoint> neighbours;

    private ChitonCavePoint previousStepAlongPath = null;
    private Integer currentPathCost = null;

    public ChitonCavePoint(int row, int column, int riskLevel) {
        this.row = row;
        this.column = column;
        this.riskLevel = riskLevel;

        if (row == 0 && column == 0) {
            currentPathCost = 0;
        }
    }

    public void setNeighbours(List<ChitonCavePoint> neighbours) {
        this.neighbours = neighbours;
    }

    public void initializePreviousStepAlongPath(ChitonCavePoint chitonCavePoint) {
        if (row == 0 && column == 0) {
            return;
        }

        this.previousStepAlongPath = chitonCavePoint;
    }

    public void initializeCurrentPathCost() {
        getCurrentPathCost();
    }

    public void setPreviousStepAlongPath(ChitonCavePoint chitonCavePoint) {
        if (row == 0 && column == 0) {
            return;
        }

        this.previousStepAlongPath = chitonCavePoint;
        this.currentPathCost = previousStepAlongPath.getCurrentPathCost() + riskLevel;
    }

    public int getCurrentPathCost() {
        if (currentPathCost == null) {
            currentPathCost = previousStepAlongPath.getCurrentPathCost() + riskLevel;
        }
        return currentPathCost;
    }

    public boolean optimizePathIfPossible() {
        if (row == 0 && column == 0) {
            return false;
        }

        ChitonCavePoint optimalEntryPoint = neighbours.stream().min(comparing(ChitonCavePoint::getCurrentPathCost)).orElseThrow();

        if (previousStepAlongPath != optimalEntryPoint && optimalEntryPoint.getCurrentPathCost() + riskLevel < currentPathCost) {
            setPreviousStepAlongPath(optimalEntryPoint);
            neighbours.forEach(neighbour -> neighbour.handleOptimizedNeighbour(this));
            return true;
        }

        return false;
    }

    private void handleOptimizedNeighbour(ChitonCavePoint optimizedNeighbour) {
        if (row == 0 && column == 0) {
            return;
        }

        if (previousStepAlongPath == optimizedNeighbour) {
            currentPathCost = optimizedNeighbour.getCurrentPathCost() + riskLevel;
            neighbours.forEach(neighbour -> neighbour.handleOptimizedNeighbour(this));
        }
    }

    public String toString() {
        return "(" + row + ", " + column + "): " + riskLevel;
    }
}
