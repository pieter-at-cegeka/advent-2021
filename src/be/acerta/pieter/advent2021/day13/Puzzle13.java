package be.acerta.pieter.advent2021.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static be.acerta.pieter.advent2021.InputFileReadingUtil.readFileLines;
import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class Puzzle13 {
    private static final Pattern COORDINATE_PATTERN = Pattern.compile("(\\d+),(\\d+)");
    private static final Pattern FOLDING_INSTRUCTION_PATTERN = Pattern.compile("fold along (\\w)=(\\d+)");

    public static void main(String... args) {
        List<Coordinate> coordinates = new ArrayList<>();
        List<FoldingInstruction> foldingInstructions = new ArrayList<>();

        readFileLines("puzzle13_input.txt").forEach(line -> {
            Matcher coordinateMatcher = COORDINATE_PATTERN.matcher(line);
            if (coordinateMatcher.matches()) {
                coordinates.add(new Coordinate(Integer.parseInt(coordinateMatcher.group(1)), Integer.parseInt(coordinateMatcher.group(2))));
            } else {
                Matcher foldingInstructionMatcher = FOLDING_INSTRUCTION_PATTERN.matcher(line);
                foldingInstructionMatcher.matches();

                foldingInstructions.add(new FoldingInstruction(Direction.fromAxisName(foldingInstructionMatcher.group(1)), Integer.parseInt(foldingInstructionMatcher.group(2))));
            }
        });

        List<Dot> dotsAfterOneFold = buildDots(coordinates, foldingInstructions.subList(0, 1));
        long numberOfVisibleDotsAfterOneFold = dotsAfterOneFold.stream()
                .map(Dot::getCoordinateAfterFolding)
                .distinct()
                .count();
        System.out.println(format("The number of visible dots after the first fold is %s", numberOfVisibleDotsAfterOneFold));

        print(buildDots(coordinates, foldingInstructions));
    }

    private static List<Dot> buildDots(List<Coordinate> coordinates, List<FoldingInstruction> foldingInstructions) {
        return coordinates.stream()
                .map(coordinate -> buildDot(coordinate, foldingInstructions))
                .collect(toList());
    }

    private static Dot buildDot(Coordinate coordinate, List<FoldingInstruction> foldingInstructions) {
        int xAfterFolding = coordinate.getX();
        int yAfterFolding = coordinate.getY();

        for (FoldingInstruction foldingInstruction : foldingInstructions) {
            switch (foldingInstruction.getDirection()) {
                case HORIZONTAL:
                    yAfterFolding = applyFold(yAfterFolding, foldingInstruction.getFoldedLine());
                    break;
                case VERTICAL:
                    xAfterFolding = applyFold(xAfterFolding, foldingInstruction.getFoldedLine());
                    break;
            }
        }

        return new Dot(coordinate, new Coordinate(xAfterFolding, yAfterFolding));
    }

    private static int applyFold(int originalValue, int foldedLine) {
        return originalValue < foldedLine ? originalValue : originalValue - ((originalValue - foldedLine) * 2);
    }

    private static void print(List<Dot> dots) {
        int width = 0;
        int height = 0;

        for (Dot dot : dots) {
            width = Math.max(width, dot.getCoordinateAfterFolding().getX());
            height = Math.max(height, dot.getCoordinateAfterFolding().getY());
        }

        boolean[][] coordinatesAsBooleanMatrix = new boolean[width + 1][height + 1];
        dots.forEach(dot -> {
            coordinatesAsBooleanMatrix[dot.getCoordinateAfterFolding().getX()][dot.getCoordinateAfterFolding().getY()] = true;
        });

        for (int x = 0; x <= height; x++) {
            for (int y = 0; y <= width; y++) {
                System.out.print(coordinatesAsBooleanMatrix[y][x] ? '#' : '.');
            }
            System.out.println();
        }
    }
}
