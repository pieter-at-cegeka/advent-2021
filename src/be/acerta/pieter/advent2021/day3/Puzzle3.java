package be.acerta.pieter.advent2021.day3;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle3 {
    public static void main(String... args) {
        DiagnosticReport diagnosticReport = new DiagnosticReport(InputFileReadingUtil.readFileLines("puzzle3_input.txt").stream()
                .map(Measurement::new)
                .collect(toList()));

        long gammaRate = diagnosticReport.calculateGammaRate();
        long epsilonRate = diagnosticReport.calculateEpsilonRate();
        long solution = gammaRate * epsilonRate;

        System.out.println(format("Gamma rate: %s, epsilon rate: %s, solution: %s",
                gammaRate,
                epsilonRate,
                solution));

        int oxygenGeneratorRating = diagnosticReport.calculateOxygenGeneratorRating();
        int co2ScrubberRating = diagnosticReport.calculateCO2ScrubberRating();
        int secondSolution = oxygenGeneratorRating * co2ScrubberRating;

        System.out.println(format("Oxygen generator rating: %s, CO2 scrubber rating : %s, solution: %s",
                oxygenGeneratorRating,
                co2ScrubberRating,
                secondSolution));
    }
}
