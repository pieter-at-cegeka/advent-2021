package be.acerta.pieter.advent2021.day3;

import java.util.Arrays;

public class Measurement {
    private final boolean[] digits;

    public Measurement(String digitsAsString) {
        this.digits = new boolean[digitsAsString.length()];
        char[] digitsAsChars = digitsAsString.toCharArray();

        for (int i = 0; i < digitsAsChars.length; i++) {
            digits[i] = digitsAsChars[i] == '1';
        }
    }

    public int length() {
        return digits.length;
    }

    public boolean getDigit(int position) {
        return digits[position];
    }

    public int asInteger() {
        int value = 0;
        for (int i = 0; i < digits.length; i++) {
            boolean digit = digits[i];
            if (digit) {
                value += Math.pow(2, digits.length - 1 - i);
            }
        }

        return value;
    }
}
