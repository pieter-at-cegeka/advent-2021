package be.acerta.pieter.advent2021.day5;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static java.lang.String.format;

public class Puzzle5B {
    public static void main(String... args) {
        List<String> vectorsAsStrings = InputFileReadingUtil.readFileLines("puzzle5_input.txt");
        List<Vector> vectors = new ArrayList<>(vectorsAsStrings.size());

        int maxXCoordinate = 0, maxYCoordinate = 0;
        Pattern pattern = Pattern.compile("^(.+),(.+) -> (.+),(.+)$");
        for (String vectorAsString : vectorsAsStrings) {
            Matcher matcher = pattern.matcher(vectorAsString);
            matcher.matches();
            int startX = Integer.parseInt(matcher.group(1));
            int startY = Integer.parseInt(matcher.group(2));
            int endX = Integer.parseInt(matcher.group(3));
            int endY = Integer.parseInt(matcher.group(4));

            maxXCoordinate = IntStream.of(maxXCoordinate, startX, endX).max().getAsInt();
            maxYCoordinate = IntStream.of(maxYCoordinate, startY, endY).max().getAsInt();

            vectors.add(new Vector(new Point(startX, startY), new Point(endX, endY)));
        }

        int numberOfPointsWithMultipleMatches = 0;
        for (int x = 0; x <= maxXCoordinate; x++) {
            for (int y = 0; y <= maxYCoordinate; y++) {
                Point point = new Point(x, y);
                if (vectors.stream()
                        .filter(vector -> vector.contains(point))
                        .count() >= 2) {
                    numberOfPointsWithMultipleMatches++;
                }
            }
        }

        System.out.println(format("Solution is %s", numberOfPointsWithMultipleMatches));
    }
}
