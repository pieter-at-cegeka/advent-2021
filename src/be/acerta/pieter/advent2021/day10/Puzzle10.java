package be.acerta.pieter.advent2021.day10;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle10 {
    public static void main(String... args) {
        List<Line> lines = InputFileReadingUtil.readFileLines("puzzle10_input.txt").stream()
                .map(Line::new)
                .collect(toList());

        int sumOfSyntaxErrorScores = lines.stream()
                .map(Line::getSyntaxErrorScore)
                .filter(Optional::isPresent)
                .mapToInt(Optional::get)
                .sum();

        List<Long> completionScores = lines.stream()
                .map(Line::getCompletionScore)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted()
                .collect(toList());

        long middleCompletionScore = completionScores.get((completionScores.size() - 1) / 2);

        System.out.println(format("The sum of syntax error scores is %s, the middle completion score is %s", sumOfSyntaxErrorScores, middleCompletionScore));
    }
}
