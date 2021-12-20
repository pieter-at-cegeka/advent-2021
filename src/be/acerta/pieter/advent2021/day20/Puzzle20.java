package be.acerta.pieter.advent2021.day20;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.List;

import static java.lang.String.format;

public class Puzzle20 {
    public static void main(String... args) {
        List<String> inputLines = InputFileReadingUtil.readFileLines("puzzle20_input.txt");

        EnhancementAlgorithm enhancementAlgorithm = new EnhancementAlgorithm(inputLines.get(0));

        Image image = new Image(inputLines.subList(2, inputLines.size()));

        for (int steps = 0; steps < 2; steps++) {
            image = image.enhance(enhancementAlgorithm);
        }

        int numberOfLitPixelsAfter2Enhancements = image.getNumberOfLitPixels();

        for (int steps = 2; steps < 50; steps++) {
            image = image.enhance(enhancementAlgorithm);
        }
        int numberOfLitPixelsAfter50Enhancements = image.getNumberOfLitPixels();

        System.out.println(format("There are %s lit pixels after 2 enhancements", numberOfLitPixelsAfter2Enhancements));
        System.out.println(format("There are %s lit pixels after 50 enhancements", numberOfLitPixelsAfter50Enhancements));
    }
}
