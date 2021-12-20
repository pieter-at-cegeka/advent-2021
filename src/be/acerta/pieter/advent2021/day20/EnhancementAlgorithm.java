package be.acerta.pieter.advent2021.day20;

public class EnhancementAlgorithm {
    private final boolean[] indexedPixels;

    public EnhancementAlgorithm(String enhancementAlgorithmAsString) {
        indexedPixels = new boolean[enhancementAlgorithmAsString.length()];
        for (int index = 0; index < indexedPixels.length; index++) {
            indexedPixels[index] = enhancementAlgorithmAsString.charAt(index) == '#';
        }
    }

    public boolean flipsInfiniteField() {
        return indexedPixels[0];
    }

    public boolean getPixel(int algorithmIndex) {
        return indexedPixels[algorithmIndex];
    }
}
