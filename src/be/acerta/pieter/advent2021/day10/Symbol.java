package be.acerta.pieter.advent2021.day10;

import java.util.Set;

public enum Symbol {
    NORMAL_OPEN,
    NORMAL_CLOSE,
    SQUARE_OPEN,
    SQUARE_CLOSE,
    CURLED_OPEN,
    CURLED_CLOSE,
    HOOKED_OPEN,
    HOOKED_CLOSE;

    public static final Set<Symbol> OPENING_SYMBOLS = Set.of(NORMAL_OPEN, SQUARE_OPEN, CURLED_OPEN, HOOKED_OPEN);

    public static Symbol ofCharacter(char character) {
        switch (character) {
            case '(': return NORMAL_OPEN;
            case ')': return NORMAL_CLOSE;
            case '[': return SQUARE_OPEN;
            case ']': return SQUARE_CLOSE;
            case '{': return CURLED_OPEN;
            case '}': return CURLED_CLOSE;
            case '<': return HOOKED_OPEN;
            case '>': return HOOKED_CLOSE;
        }

        throw new IllegalArgumentException("Illegal character: " + character);
    }

    public boolean opens() {
        return OPENING_SYMBOLS.contains(this);
    }

    public boolean matches(Symbol other) {
        return (this == NORMAL_OPEN && other == NORMAL_CLOSE)
                || (this == SQUARE_OPEN && other == SQUARE_CLOSE)
                || (this == CURLED_OPEN && other == CURLED_CLOSE)
                || (this == HOOKED_OPEN && other == HOOKED_CLOSE);
    }

    public int getSyntaxErrorScore() {
        switch (this) {
            case NORMAL_CLOSE: return 3;
            case SQUARE_CLOSE: return 57;
            case CURLED_CLOSE: return 1197;
            case HOOKED_CLOSE: return 25137;
        }

        throw new IllegalStateException("Symbol is not a closing symbol: " + this);
    }

    public long getCompletionScore() {
        switch (this) {
            case NORMAL_OPEN: return 1L;
            case SQUARE_OPEN: return 2L;
            case CURLED_OPEN: return 3L;
            case HOOKED_OPEN: return 4L;
        }

        throw new IllegalStateException("Symbol is not a closing symbol: " + this);
    }
}
