package be.acerta.pieter.advent2021.day11;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import static java.lang.String.format;

public class Puzzle11B {
    public static void main(String... args) {
        OctopusGrid octopusGrid = new OctopusGrid(InputFileReadingUtil.readFileLines("puzzle11_input.txt"));

        Integer firstStepAtWhichAllOctopusesFlash = null;
        int currentStep = 1;
        while (firstStepAtWhichAllOctopusesFlash == null) {
            boolean allOctopusesFlashed = octopusGrid.executeStep(currentStep);
            if (allOctopusesFlashed) {
                firstStepAtWhichAllOctopusesFlash = currentStep;
            }
            currentStep += 1;
        }

        System.out.println(format("The first step at which all octopuses flash is %s", firstStepAtWhichAllOctopusesFlash));
    }
}
