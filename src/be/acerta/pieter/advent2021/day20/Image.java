package be.acerta.pieter.advent2021.day20;

import java.util.List;

public class Image {
    private final boolean[][] imageAsPixels;
    private final boolean pixelOfInfiniteField;

    public Image(boolean[][] imageAsPixels, boolean pixelOfInfiniteField) {
        this.imageAsPixels = imageAsPixels;
        this.pixelOfInfiniteField = pixelOfInfiniteField;
    }

    public Image(List<String> pixelRowsAsStrings) {
        this.pixelOfInfiniteField = false;

        imageAsPixels = new boolean[pixelRowsAsStrings.size()][pixelRowsAsStrings.get(0).length()];

        for (int row = 0; row < imageAsPixels.length; row++) {
            for (int column = 0; column < imageAsPixels[row].length; column++) {
                imageAsPixels[row][column] = pixelRowsAsStrings.get(row).charAt(column) == '#';
            }
        }
    }

    private boolean pixelAt(int row, int column) {
        if (row < 0 || column < 0 || row > imageAsPixels.length - 1 || column > imageAsPixels[0].length - 1) {
            return pixelOfInfiniteField;
        }

        return imageAsPixels[row][column];
    }

    public int getNumberOfLitPixels() {
        int numberOfLitPixels = 0;
        for (int row = 0; row < imageAsPixels.length; row++) {
            for (int column = 0; column < imageAsPixels[row].length; column++) {
                numberOfLitPixels += imageAsPixels[row][column] ? 1 : 0;
            }
        }
        return numberOfLitPixels;
    }

    public Image enhance(EnhancementAlgorithm enhancementAlgorithm) {
        boolean[][] enhancedImageAsPixels = new boolean[imageAsPixels.length + 2][imageAsPixels[0].length + 2];

        for (int row = 0; row < enhancedImageAsPixels.length; row++) {
            for (int column = 0; column < enhancedImageAsPixels[row].length; column++) {
                int algorithmIndex = determineAlgorithmIndexOFEnhancedPixelAt(row, column);
                enhancedImageAsPixels[row][column] = enhancementAlgorithm.getPixel(algorithmIndex);
            }
        }

        return new Image(enhancedImageAsPixels, enhancementAlgorithm.flipsInfiniteField() ? !this.pixelOfInfiniteField : this.pixelOfInfiniteField);
    }

    private int determineAlgorithmIndexOFEnhancedPixelAt(int row, int column) {
        return (pixelAt(row - 2, column - 2) ? 256 : 0) +
                (pixelAt(row - 2, column - 1) ? 128 : 0) +
                (pixelAt(row - 2, column) ? 64 : 0) +
                (pixelAt(row - 1, column - 2) ? 32 : 0) +
                (pixelAt(row - 1, column - 1) ? 16 : 0) +
                (pixelAt(row - 1, column) ? 8 : 0) +
                (pixelAt(row, column - 2) ? 4 : 0) +
                (pixelAt(row, column - 1) ? 2 : 0) +
                (pixelAt(row, column) ? 1 : 0);
    }
}