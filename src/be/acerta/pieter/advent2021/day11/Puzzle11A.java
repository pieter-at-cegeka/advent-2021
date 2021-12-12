package be.acerta.pieter.advent2021.day11;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

public class Puzzle11A {
    public static void main(String... args) {
        OctopusGrid octopusGrid = new OctopusGrid(InputFileReadingUtil.readFileLines("puzzle11_input.txt"));

        for (int currentStep = 1; currentStep <= 100; currentStep++) {
            octopusGrid.executeStep(currentStep);
        }

        int numberOfFlashesOf100Steps = octopusGrid.getNumberOfFlashes();

        System.out.println(String.format("Number of flashes after 100 steps is %s", numberOfFlashesOf100Steps));
    }
}
