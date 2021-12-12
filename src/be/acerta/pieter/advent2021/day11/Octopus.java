package be.acerta.pieter.advent2021.day11;

import java.util.HashSet;
import java.util.Set;

public class Octopus {
    private int currentEnergyLevel;
    private final Set<Integer> stepsWhereThisOctopusFlashed;

    public Octopus(int initialEnergyLevel) {
        this.currentEnergyLevel = initialEnergyLevel;
        this.stepsWhereThisOctopusFlashed = new HashSet<>();
    }

    public int getCurrentEnergyLevel() {
        return currentEnergyLevel;
    }

    public boolean increaseEnergyLevel(int currentStep) {
        if (stepsWhereThisOctopusFlashed.contains(currentStep)) {
            return false;
        } else {
            currentEnergyLevel += 1;

            if (currentEnergyLevel == 10) {
                stepsWhereThisOctopusFlashed.add(currentStep);
                currentEnergyLevel = 0;
                return true;
            } else {
                return false;
            }
        }
    }

    public int getNumberOfFlashes() {
        return stepsWhereThisOctopusFlashed.size();
    }

    public boolean flashedAtStep(int step) {
        return stepsWhereThisOctopusFlashed.contains(step);
    }
}
