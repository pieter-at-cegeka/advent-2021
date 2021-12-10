package be.acerta.pieter.advent2021.day9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class HeightMap {
    private final Point[][] points;

    public HeightMap(List<String> heightRowsAsStrings) {
        this.points = new Point[heightRowsAsStrings.size()][heightRowsAsStrings.get(0).length()];

        for (int row = 0; row < points.length; row++) {
            for (int column = 0; column < points[0].length; column++) {
                points[row][column] = new Point(row, column, Character.getNumericValue(heightRowsAsStrings.get(row).charAt(column)));
            }
        }
    }

    public List<Point> findLowPoints() {
        List<Point> lowPoints = new ArrayList<>();
        for (int row = 0; row < points.length; row++) {
            for (int column = 0; column < points[0].length; column++) {
                Point point = points[row][column];
                if (isLowPoint(point)) {
                    lowPoints.add(point);
                }
            }
        }

        return lowPoints;
    }

    private boolean isLowPoint(Point point) {
        return findNeighbours(point).stream().noneMatch(neighbour -> neighbour.getHeight() <= point.getHeight());
    }

    private List<Point> findNeighbours(Point point) {
        return Stream.of(getNorthNeighbour(point),
                        getEastNeighbour(point),
                        getSouthNeighbour(point),
                        getWestNeighbour(point))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    private Optional<Point> getNorthNeighbour(Point point) {
        if (point.getRow() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(points[point.getRow() - 1][point.getColumn()]);
        }
    }

    private Optional<Point> getEastNeighbour(Point point) {
        if (point.getColumn() == points[0].length - 1) {
            return Optional.empty();
        } else {
            return Optional.of(points[point.getRow()][point.getColumn() + 1]);
        }
    }

    private Optional<Point> getSouthNeighbour(Point point) {
        if (point.getRow() == points.length - 1) {
            return Optional.empty();
        } else {
            return Optional.of(points[point.getRow() + 1][point.getColumn()]);
        }
    }

    private Optional<Point> getWestNeighbour(Point point) {
        if (point.getColumn() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(points[point.getRow()][point.getColumn() - 1]);
        }
    }

    public List<Basin> findBasins() {
        return findLowPoints().stream()
                .map(this::findBasin)
                .collect(toList());
    }

    private Basin findBasin(Point lowPoint) {
        Set<Point> pointsInBasin = new HashSet<>();
        pointsInBasin.add(lowPoint);

        boolean currentIterationAddedPoints = false;
        do {
            currentIterationAddedPoints = false;

            for (Point pointInBasin : Set.copyOf(pointsInBasin)) {
                List<Point> neighboursInBasin = findNeighbours(pointInBasin).stream()
                        .filter(neighbour -> neighbour.getHeight() < 9)
                        .collect(toList());

                currentIterationAddedPoints |= pointsInBasin.addAll(neighboursInBasin);
            }

        } while (currentIterationAddedPoints);

        return new Basin(lowPoint, pointsInBasin);
    }
}
