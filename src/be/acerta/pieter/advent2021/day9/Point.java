package be.acerta.pieter.advent2021.day9;

public class Point {
    private final int row;
    private final int column;
    private final int height;

    public Point(int row, int column, int height) {
        this.row = row;
        this.column = column;
        this.height = height;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getHeight() {
        return height;
    }

    public int getRiskLevel() {
        return height + 1;
    }
}
