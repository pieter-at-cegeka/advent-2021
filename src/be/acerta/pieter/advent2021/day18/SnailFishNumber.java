package be.acerta.pieter.advent2021.day18;

import java.util.Optional;

import static java.lang.Character.getNumericValue;
import static java.lang.Character.isDigit;

public class SnailFishNumber implements SnailFishNumberElement {
    private final String snailFishNumberAsString;

    public SnailFishNumber(String snailFishNumberAsString) {
        this.snailFishNumberAsString = snailFishNumberAsString;

    }

    @Override
    public int calculateMagnitude() {
        int indexOfOwnComma = findIndexOfOwnComma();

        SnailFishNumberElement leftElement = SnailFishNumberElement.of(snailFishNumberAsString.substring(1, indexOfOwnComma));
        SnailFishNumberElement rightElement = SnailFishNumberElement.of(snailFishNumberAsString.substring(indexOfOwnComma + 1, snailFishNumberAsString.length() - 1));

        return leftElement.calculateMagnitude() * 3 + rightElement.calculateMagnitude() * 2;
    }

    private int findIndexOfOwnComma() {
        int currentDepth = 0;

        for (int index = 0; index < snailFishNumberAsString.length(); index++) {
            switch (snailFishNumberAsString.charAt(index)) {
                case '[':
                    currentDepth++;
                    break;
                case ']':
                    currentDepth--;
                    break;
                case ',':
                    if (currentDepth == 1) {
                        return index;
                    }
            }
        }

        throw new IllegalArgumentException("Can't find own comma in " + snailFishNumberAsString);
    }

    public SnailFishNumber add(SnailFishNumber other) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[').append(snailFishNumberAsString).append(',').append(other.snailFishNumberAsString).append(']');

        performFullReduction(stringBuilder);

        return new SnailFishNumber(stringBuilder.toString());
    }

    public static void performFullReduction(StringBuilder protoSnailFishNumber) {
        boolean changedByReduction;

        do {
            changedByReduction = findAndApplyReductionRule(protoSnailFishNumber);
        } while (changedByReduction);
    }

    public static boolean findAndApplyReductionRule(StringBuilder protoSnailFishNumber) {
        return explodeLeftmostOverIndentedPair(protoSnailFishNumber)
                || splitLeftmostHumanNumberOver9(protoSnailFishNumber);
    }

    public static boolean explodeLeftmostOverIndentedPair(StringBuilder protoSnailFishNumber) {
        int currentDepth = 0;

        for (int index = 0; index < protoSnailFishNumber.length(); index++) {
            switch (protoSnailFishNumber.charAt(index)) {
                case '[':
                    if (currentDepth == 4) {
                        explodePairStartingAt(protoSnailFishNumber, index);
                        return true;
                    } else {
                        currentDepth++;
                    }
                    break;
                case ']':
                    currentDepth--;
                    break;
            }
        }

        return false;
    }

    public static void explodePairStartingAt(StringBuilder protoSnailFishNumber, int indexOfOpeningBrace) {
        int indexOfComma = protoSnailFishNumber.indexOf(",", indexOfOpeningBrace);
        int indexOfClosingBrace = protoSnailFishNumber.indexOf("]", indexOfOpeningBrace);

        int leftNumber = Integer.parseInt(protoSnailFishNumber.substring(indexOfOpeningBrace + 1, indexOfComma));
        int rightNumber = Integer.parseInt(protoSnailFishNumber.substring(indexOfComma + 1, indexOfClosingBrace));

        findIndexOfNextHumanNumber(protoSnailFishNumber, indexOfClosingBrace + 1)
                .ifPresent(indexOfNextNumber -> replaceHumanNumberBySum(protoSnailFishNumber, indexOfNextNumber, rightNumber));
        protoSnailFishNumber.replace(indexOfOpeningBrace, indexOfClosingBrace + 1, "0");
        findIndexOfPreviousHumanNumber(protoSnailFishNumber, indexOfOpeningBrace - 1)
                .ifPresent(indexOfPreviousNumber -> replaceHumanNumberBySum(protoSnailFishNumber, indexOfPreviousNumber, leftNumber));
        ;
    }

    private static void replaceHumanNumberBySum(StringBuilder protoSnailFishNumber, Integer indexOfFirstDigit, int addend) {
        int indexOfDigit = indexOfFirstDigit;
        int originalNumber = 0;
        while (isDigit(protoSnailFishNumber.charAt(indexOfDigit))) {
            originalNumber *= 10;
            originalNumber += getNumericValue(protoSnailFishNumber.charAt(indexOfDigit++));
        }

        int sum = originalNumber + addend;
        protoSnailFishNumber.replace(indexOfFirstDigit, indexOfDigit, Integer.toString(sum));
    }

    private static Optional<Integer> findIndexOfNextHumanNumber(StringBuilder protoSnailFishNumber, int indexFrom) {
        for (int index = indexFrom; index < protoSnailFishNumber.length(); index++) {
            char character = protoSnailFishNumber.charAt(index);
            if (isDigit(character)) {
                return Optional.of(index);
            }
        }

        return Optional.empty();
    }

    private static Optional<Integer> findIndexOfPreviousHumanNumber(StringBuilder protoSnailFishNumber, int indexFrom) {
        for (int index = indexFrom; index >= 0; index--) {
            char character = protoSnailFishNumber.charAt(index);
            if (isDigit(character)) {
                while (isDigit(protoSnailFishNumber.charAt(index - 1))) {
                    index--;
                }
                return Optional.of(index);
            }
        }

        return Optional.empty();
    }

    private static boolean splitLeftmostHumanNumberOver9(StringBuilder protoSnailFishNumber) {
        for (int index = 0; index < protoSnailFishNumber.length(); index++) {
            if (isDigit(protoSnailFishNumber.charAt(index)) && isDigit(protoSnailFishNumber.charAt(index + 1))) {
                splitHumanNumberAt(protoSnailFishNumber, index);
                return true;
            }
        }
        return false;
    }

    private static void splitHumanNumberAt(StringBuilder protoSnailFishNumber, int indexOfFirstDigit) {
        int indexOfDigit = indexOfFirstDigit;
        int originalNumber = 0;
        while (isDigit(protoSnailFishNumber.charAt(indexOfDigit))) {
            originalNumber *= 10;
            originalNumber += getNumericValue(protoSnailFishNumber.charAt(indexOfDigit++));
        }

        int leftNumber = originalNumber / 2;
        int rightNumber = originalNumber - leftNumber;

        protoSnailFishNumber.replace(indexOfFirstDigit, indexOfDigit, "[" + leftNumber + "," + rightNumber + "]");
    }

    public String toString() {
        return snailFishNumberAsString;
    }
}
