package be.acerta.pieter.advent2021.day15;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ChitonCave {
    private final ChitonCavePoint[][] chitonCavePoints;

    public ChitonCave(List<String> rowsAsStrings) {
        chitonCavePoints = new ChitonCavePoint[rowsAsStrings.size()][rowsAsStrings.get(0).length()];

        for (int row = 0; row < rowsAsStrings.size(); row++) {
            for (int column = 0; column < rowsAsStrings.get(row).length(); column++) {
                chitonCavePoints[row][column] = new ChitonCavePoint(row, column, Character.getNumericValue(rowsAsStrings.get(row).charAt(column)));
            }
        }

        setInitialPathsAndNeighbours();

        boolean shorterPathFound;
        int iterations = 0;
        do {
            iterations++;
            shorterPathFound = false;

            for (int row = 0; row < rowsAsStrings.size(); row++) {
                for (int column = 0; column < rowsAsStrings.get(row).length(); column++) {
                    shorterPathFound |= chitonCavePoints[row][column].optimizePathIfPossible();
                }
            }
        } while (shorterPathFound);
    }

    public int getRiskLevelsOfOptimalPath() {
        return chitonCavePoints[chitonCavePoints.length -1][chitonCavePoints[0].length - 1].getCurrentPathCost();
    }

    private void setInitialPathsAndNeighbours() {
        for (int row = 1; row < chitonCavePoints.length; row++) {
            chitonCavePoints[row][0].initializePreviousStepAlongPath(chitonCavePoints[row - 1][0]);
        }

        for (int row = 0; row < chitonCavePoints.length; row++) {
            for (int column = 1; column < chitonCavePoints[row].length; column++)
                chitonCavePoints[row][column].initializePreviousStepAlongPath(chitonCavePoints[row][column - 1]);
        }

        for (int row = 0; row < chitonCavePoints.length; row++) {
            for (int column = 0; column < chitonCavePoints[row].length; column++) {
                chitonCavePoints[row][column].setNeighbours(findNeighbours(row, column));
                chitonCavePoints[row][column].initializeCurrentPathCost();
            }
        }
    }

    private List<ChitonCavePoint> findNeighbours(int row, int column) {
        ChitonCavePoint northNeighbour = row == 0 ? null : chitonCavePoints[row - 1][column];
        ChitonCavePoint eastNeighbour = column == chitonCavePoints[0].length - 1 ? null : chitonCavePoints[row][column + 1];
        ChitonCavePoint southNeighbour = row == chitonCavePoints.length - 1 ? null : chitonCavePoints[row + 1][column];
        ChitonCavePoint westNeighbour = column == 0 ? null : chitonCavePoints[row][column - 1];

        return Stream.of(northNeighbour, eastNeighbour, southNeighbour, westNeighbour)
                .filter(Objects::nonNull)
                .collect(toList());
    }
}
