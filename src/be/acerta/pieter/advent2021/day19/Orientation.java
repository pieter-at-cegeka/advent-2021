package be.acerta.pieter.advent2021.day19;

import java.util.List;

@FunctionalInterface
public interface Orientation {
    BeaconDetection translate(BeaconDetection beaconDetection);

    public final List<Orientation> ALL_ORIENTATIONS = List.of(
            beaconDetection -> new BeaconDetection(beaconDetection.getLeftValue(), beaconDetection.getMiddleValue(), beaconDetection.getRightValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getLeftValue(), beaconDetection.getRightValue(), beaconDetection.getMiddleValue() * -1),
            beaconDetection -> new BeaconDetection(beaconDetection.getLeftValue(), beaconDetection.getMiddleValue() * -1, beaconDetection.getRightValue() * -1),
            beaconDetection -> new BeaconDetection(beaconDetection.getLeftValue(), beaconDetection.getRightValue() * -1, beaconDetection.getMiddleValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getLeftValue() * -1, beaconDetection.getMiddleValue() * -1, beaconDetection.getRightValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getLeftValue() * -1, beaconDetection.getRightValue(), beaconDetection.getMiddleValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getLeftValue() * -1, beaconDetection.getMiddleValue(), beaconDetection.getRightValue() * -1),
            beaconDetection -> new BeaconDetection(beaconDetection.getLeftValue() * -1, beaconDetection.getRightValue() * -1, beaconDetection.getMiddleValue() * -1),
            beaconDetection -> new BeaconDetection(beaconDetection.getMiddleValue(), beaconDetection.getLeftValue() * -1, beaconDetection.getRightValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getMiddleValue(), beaconDetection.getRightValue(), beaconDetection.getLeftValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getMiddleValue(), beaconDetection.getLeftValue(), beaconDetection.getRightValue() * -1),
            beaconDetection -> new BeaconDetection(beaconDetection.getMiddleValue(), beaconDetection.getRightValue() * -1, beaconDetection.getLeftValue() * -1),
            beaconDetection -> new BeaconDetection(beaconDetection.getMiddleValue() * -1, beaconDetection.getLeftValue(), beaconDetection.getRightValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getMiddleValue() * -1, beaconDetection.getRightValue(), beaconDetection.getLeftValue() * -1),
            beaconDetection -> new BeaconDetection(beaconDetection.getMiddleValue() * -1, beaconDetection.getLeftValue() * -1, beaconDetection.getRightValue() * -1),
            beaconDetection -> new BeaconDetection(beaconDetection.getMiddleValue() * -1, beaconDetection.getRightValue() * -1, beaconDetection.getLeftValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getRightValue(), beaconDetection.getLeftValue(), beaconDetection.getMiddleValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getRightValue(), beaconDetection.getMiddleValue(), beaconDetection.getLeftValue() * -1),
            beaconDetection -> new BeaconDetection(beaconDetection.getRightValue(), beaconDetection.getLeftValue() * -1, beaconDetection.getMiddleValue() * -1),
            beaconDetection -> new BeaconDetection(beaconDetection.getRightValue(), beaconDetection.getMiddleValue() * -1, beaconDetection.getLeftValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getRightValue() * -1, beaconDetection.getLeftValue() * -1, beaconDetection.getMiddleValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getRightValue() * -1, beaconDetection.getMiddleValue(), beaconDetection.getLeftValue()),
            beaconDetection -> new BeaconDetection(beaconDetection.getRightValue() * -1, beaconDetection.getLeftValue(), beaconDetection.getMiddleValue() * -1),
            beaconDetection -> new BeaconDetection(beaconDetection.getRightValue() * -1, beaconDetection.getMiddleValue() * -1, beaconDetection.getLeftValue() * -1)
            );
}
