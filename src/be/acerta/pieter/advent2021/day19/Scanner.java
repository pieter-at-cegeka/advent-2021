package be.acerta.pieter.advent2021.day19;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class Scanner {
    private final Location location;
    private final Orientation orientation;
    private final ScannerOutput scannerOutput;
    private final Set<Location> beaconLocations;

    public Scanner(Location location, Orientation orientation, ScannerOutput scannerOutput) {
        this.location = location;
        this.orientation = orientation;
        this.scannerOutput = scannerOutput;

        this.beaconLocations = scannerOutput.getBeaconDetections().stream()
                .map(orientation::translate)
                .map(location::transpose)
                .collect(toSet());
    }

    public Location getLocation() {
        return location;
    }

    public Set<Location> getBeaconLocations() {
        return beaconLocations;
    }
}
