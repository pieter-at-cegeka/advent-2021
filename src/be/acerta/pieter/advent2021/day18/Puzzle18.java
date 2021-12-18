package be.acerta.pieter.advent2021.day18;

import java.util.List;
import java.util.stream.Stream;

import static be.acerta.pieter.advent2021.InputFileReadingUtil.readFileLines;
import static be.acerta.pieter.advent2021.day18.SnailFishNumber.explodeLeftmostOverIndentedPair;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle18 {
    public static void main(String... args) {
        Stream.of(
                "[[[[[9,8],1],2],3],4]",
                "[7,[6,[5,[4,[3,2]]]]]",
                "[[6,[5,[4,[3,2]]]],1]",
                "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]",
                "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]").forEach(protoSnailFishNumber -> {
                    StringBuilder protoSnailFishNumberAsStringBuilder = new StringBuilder(protoSnailFishNumber);
                    explodeLeftmostOverIndentedPair(protoSnailFishNumberAsStringBuilder);
                    System.out.println(format("Proto number %s becomes %s after single explosion",
                            protoSnailFishNumber,
                            protoSnailFishNumberAsStringBuilder));
                }
        );

        System.out.println(format("[[[[4,3],4],4],[7,[[8,4],9]]] + [1,1] results in snail fish number %s",
                new SnailFishNumber("[[[[4,3],4],4],[7,[[8,4],9]]]").add(new SnailFishNumber("[1,1]"))));

        Stream.of(
                "[[1,2],[[3,4],5]]",
                "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]",
                "[[[[1,1],[2,2]],[3,3]],[4,4]]",
                "[[[[3,0],[5,3]],[4,4]],[5,5]]",
                "[[[[5,0],[7,4]],[5,5]],[6,6]]",
                "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]").forEach(snailFishNumberAsString ->
                System.out.println(format("Snail fish number %s has magnitude %s",
                        snailFishNumberAsString,
                        new SnailFishNumber(snailFishNumberAsString).calculateMagnitude())));

        List<SnailFishNumber> testSnailFishNumbers = readFileLines("puzzle18_testinput.txt").stream()
                .map(SnailFishNumber::new)
                .collect(toList());
        SnailFishNumber sumOfTestSnailFishNumbers = testSnailFishNumbers.stream()
                .reduce(testSnailFishNumbers.get(0), SnailFishNumber::add);
        int magnitudeOfSumOfTwoTestEntriesWithLargestMagnitude = findMagnitudeOfSumOfTwoEntriesWithLargestMagnitude(testSnailFishNumbers);
        System.out.println(format("The test input adds up to %s with a magnitude of %s, the largest magnitude of the sum of any two different entries is %s",
                sumOfTestSnailFishNumbers,
                sumOfTestSnailFishNumbers.calculateMagnitude(),
                magnitudeOfSumOfTwoTestEntriesWithLargestMagnitude));

        List<SnailFishNumber> snailFishNumbers = readFileLines("puzzle18_input.txt").stream()
                .map(SnailFishNumber::new)
                .collect(toList());
        SnailFishNumber sumOfSnailFishNumbers = snailFishNumbers.stream()
                .skip(1)
                .reduce(snailFishNumbers.get(0), SnailFishNumber::add);
        int magnitudeOfSumOfTwoEntriesWithLargestMagnitude = findMagnitudeOfSumOfTwoEntriesWithLargestMagnitude(snailFishNumbers);
        System.out.println(format("The input adds up to %s with a magnitude of %s, the largest magnitude of the sum of any two different entries is %s",
                sumOfSnailFishNumbers,
                sumOfSnailFishNumbers.calculateMagnitude(),
                magnitudeOfSumOfTwoEntriesWithLargestMagnitude));
    }

    private static int findMagnitudeOfSumOfTwoEntriesWithLargestMagnitude(List<SnailFishNumber> snailFishNumbers) {
        return snailFishNumbers.stream()
                .flatMap(oneSnailFishNumber -> snailFishNumbers.stream()
                        .filter(otherSnailFishNumber -> !oneSnailFishNumber.equals(otherSnailFishNumber))
                        .map(oneSnailFishNumber::add))
                .mapToInt(SnailFishNumber::calculateMagnitude)
                .max().orElseThrow();
    }
}
