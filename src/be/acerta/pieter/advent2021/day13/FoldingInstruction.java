package be.acerta.pieter.advent2021.day13;

public class FoldingInstruction {
    private final Direction direction;
    private final int foldedLine;

    public FoldingInstruction(Direction direction, int foldedLine) {
        this.direction = direction;
        this.foldedLine = foldedLine;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getFoldedLine() {
        return foldedLine;
    }
}
