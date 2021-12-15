package be.acerta.pieter.advent2021.day15;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle15 {
    public static void main(String... args) {
        List<String> lines = InputFileReadingUtil.readFileLines("puzzle15_input.txt");
        ChitonCave smallChitonCave = new ChitonCave(lines);

        List<String> expandedLines = expand(lines);
        ChitonCave expandedChitonCave = new ChitonCave(expandedLines);

        int riskLevelsOfOptimalPathOfSmallCave = smallChitonCave.getRiskLevelsOfOptimalPath();
        int riskLevelsOfOptimalPathOfExpandedCave = expandedChitonCave.getRiskLevelsOfOptimalPath();

        System.out.println(format("The risk level of the optimal path in the small cabe is %s, in the expanded cave it is %s", riskLevelsOfOptimalPathOfSmallCave, riskLevelsOfOptimalPathOfExpandedCave));
    }

    private static List<String> expand(List<String> lines) {
        List<String> expandedColumns = lines.stream()
                .map(line -> expand(line))
                .collect(toList());

        List<String> expandedRows = new ArrayList<>();
        for (int repetition = 0; repetition < 5; repetition++) {
            for (String line : expandedColumns) {
                expandedRows.add(increaseBy(line, repetition));
            }
        }

        return expandedRows;
    }

    private static String expand(String line) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int repetition = 0; repetition < 5; repetition++) {
            stringBuffer.append(increaseBy(line, repetition));
        }

        return stringBuffer.toString();
    }

    private static String increaseBy(String line, int increment) {
        StringBuffer buffer = new StringBuffer();
        for (int charIndex = 0; charIndex < line.length(); charIndex++) {
            int value = Character.getNumericValue(line.charAt(charIndex)) + increment;
            if (value > 9) {
                value -= 9;
            }
            buffer.append(value);
        }

        return buffer.toString();
    }
}
