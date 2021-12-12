package be.acerta.pieter.advent2021.day11;

import java.util.List;
import java.util.stream.Stream;

public class OctopusGrid {
    private final Octopus[][] grid;

    public OctopusGrid(List<String> rowsOfInitialEnergyLevelsAsStrings) {
        this.grid = new Octopus[rowsOfInitialEnergyLevelsAsStrings.size()][rowsOfInitialEnergyLevelsAsStrings.get(0).length()];

        for (int x = 0; x < rowsOfInitialEnergyLevelsAsStrings.size(); x++) {
            String rowOfInitialEnergyLevelsAsString = rowsOfInitialEnergyLevelsAsStrings.get(x);
            for (int y = 0; y < rowOfInitialEnergyLevelsAsString.length(); y++) {
                grid[x][y] = new Octopus(Character.getNumericValue(rowOfInitialEnergyLevelsAsString.charAt(y)));
            }
        }
    }

    public boolean executeStep(int currentStep) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                increaseEnergyLevelOfOctopusAt(currentStep, x, y);
            }
        }

        return Stream.of(grid)
                .flatMap(Stream::of)
                .allMatch(octopus -> octopus.flashedAtStep(currentStep));
    }

    private void increaseEnergyLevelOfOctopusAt(int currentStep, int x, int y) {
        boolean energyLevelMadeOctopusFlash = grid[x][y].increaseEnergyLevel(currentStep);

        if (energyLevelMadeOctopusFlash) {
            for (int deltaX = (x == 0 ? 0 : -1); deltaX <= (x == grid.length - 1 ? 0 : 1); deltaX++) {
                for (int deltaY = (y == 0 ? 0 : -1); deltaY <= (y == grid[x].length - 1 ? 0 : 1); deltaY++) {
                    increaseEnergyLevelOfOctopusAt(currentStep, x + deltaX, y + deltaY);
                }
            }
        }
    }

    public int getNumberOfFlashes() {
        return Stream.of(grid)
                .flatMap(Stream::of)
                .mapToInt(Octopus::getNumberOfFlashes)
                .sum();
    }

    public void print() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                System.out.print(grid[x][y].getCurrentEnergyLevel());
            }
            System.out.println();
        }
        System.out.println();
    }
}
