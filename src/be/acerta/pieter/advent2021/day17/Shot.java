package be.acerta.pieter.advent2021.day17;

public class Shot {
    private final int initialXVelocity;
    private final int initialYVelocity;

    public Shot(int initialXVelocity, int initialYVelocity) {
        this.initialXVelocity = initialXVelocity;
        this.initialYVelocity = initialYVelocity;
    }

    public int getMaxHeight() {
        int height = 0;
        int currentYVelocity = initialYVelocity;
        while (currentYVelocity > 0) {
            height += currentYVelocity--;
        }
        return height;
    }
}
