package be.acerta.pieter.advent2021.day9;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.Comparator;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle9 {
    public static void main(String... args) {
        HeightMap heightMap = new HeightMap(InputFileReadingUtil.readFileLines("puzzle9_input.txt"));

        int sumOfRiskLevelsOfLowPoints = heightMap.findLowPoints().stream()
                .mapToInt(Point::getRiskLevel)
                .sum();

        List<Basin> threeLargestBasins = heightMap.findBasins().stream()
                .sorted(Comparator.comparing(Basin::size).reversed())
                .limit(3)
                .collect(toList());
        int productOfSizesOfThreeLargestBasinSizes = threeLargestBasins
                .stream().mapToInt(Basin::size)
                .reduce(1, (product, size) -> product * size);

        System.out.println(format("Sum of risk levels of low points is %s, product of sizes of three largest basins is %s", sumOfRiskLevelsOfLowPoints, productOfSizesOfThreeLargestBasinSizes));
    }
}
