package be.acerta.pieter.advent2021.day3;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class DiagnosticReport {
    private final List<Measurement> measurements;

    public DiagnosticReport(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public int calculateGammaRate() {
        return calculateGammaRate(measurements);
    }

    public int calculateEpsilonRate() {
        return calculateEpsilonRate(measurements);
    }

    public int calculateOxygenGeneratorRating() {
        int measurementLength = measurements.get(0).length();
        int currentPosition = 0;
        List<Measurement> relevantMeasurements = List.copyOf(measurements);

        while(relevantMeasurements.size() > 1) {
            boolean bitCriteria = findMostCommonDigit(relevantMeasurements, currentPosition);

            int usableCurrentPosition = currentPosition;
            relevantMeasurements = relevantMeasurements.stream()
                    .filter(measurement -> measurement.getDigit(usableCurrentPosition) == bitCriteria)
                    .collect(toList());

            currentPosition++;
            if (currentPosition > measurementLength) {
                currentPosition = 0;
            }
        }

        return relevantMeasurements.get(0).asInteger();
    }

    public int calculateCO2ScrubberRating() {
        int measurementLength = measurements.get(0).length();
        int currentPosition = 0;
        List<Measurement> relevantMeasurements = List.copyOf(measurements);

        while(relevantMeasurements.size() > 1) {
            boolean bitCriteria = !findMostCommonDigit(relevantMeasurements, currentPosition);

            int usableCurrentPosition = currentPosition;
            relevantMeasurements = relevantMeasurements.stream()
                    .filter(measurement -> measurement.getDigit(usableCurrentPosition) == bitCriteria)
                    .collect(toList());

            currentPosition++;
            if (currentPosition > measurementLength) {
                currentPosition = 0;
            }
        }

        return relevantMeasurements.get(0).asInteger();
    }

    private static int calculateGammaRate(List<Measurement> measurements) {
        int measurementLength = measurements.get(0).length();
        int gammaRate = 0;
        for (int i = 0; i < measurementLength; i++) {
            boolean digit = findMostCommonDigit(measurements, i);
            if (digit) {
                gammaRate += Math.pow(2, measurementLength - 1 - i);
            }
        }

        return gammaRate;
    }

    public static int calculateEpsilonRate(List<Measurement> measurements) {
        int measurementLength = measurements.get(0).length();
        int epsilonRate = 0;
        for (int i = 0; i < measurementLength; i++) {
            boolean digit = findMostCommonDigit(measurements, i);
            if (!digit) {
                epsilonRate += Math.pow(2, measurementLength - 1 - i);
            }
        }

        return epsilonRate;
    }

    private static boolean findMostCommonDigit(List<Measurement> measurements, int position) {
        long numberOfOneDigits = measurements.stream()
                .filter(measurement -> measurement.getDigit(position))
                .count();

        return numberOfOneDigits * 2 >= measurements.size();
    }
}
