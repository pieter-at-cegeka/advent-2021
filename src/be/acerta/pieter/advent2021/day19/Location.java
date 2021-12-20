package be.acerta.pieter.advent2021.day19;

import java.util.Objects;

import static java.lang.String.format;

public class Location {
    private final int xPosition;
    private final int yPosition;
    private final int zPosition;

    public Location(int xPosition, int yPosition, int zPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
    }

    public Location transpose(BeaconDetection beaconDetection) {
        return new Location(xPosition + beaconDetection.getLeftValue(), yPosition + beaconDetection.getMiddleValue(), zPosition + beaconDetection.getRightValue());
    }

    public Location reverseTranspose(BeaconDetection beaconDetection) {
        return new Location(xPosition - beaconDetection.getLeftValue(), yPosition - beaconDetection.getMiddleValue(), zPosition - beaconDetection.getRightValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return xPosition == location.xPosition && yPosition == location.yPosition && zPosition == location.zPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition, zPosition);
    }

    public String toString() {
        return format("(%s,%s,%s)", xPosition, yPosition, zPosition);
    }

    public int manhattanDistance(Location other) {
        return Math.abs(this.xPosition - other.xPosition) + Math.abs(this.yPosition - other.yPosition) + Math.abs(this.zPosition - other.zPosition);
    }
}
