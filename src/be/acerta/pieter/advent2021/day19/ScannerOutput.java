package be.acerta.pieter.advent2021.day19;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ScannerOutput {
    private final String name;
    private final List<BeaconDetection> beaconDetections;

    public ScannerOutput(List<String> beaconDetectionsAsString) {
        this.name = beaconDetectionsAsString.get(0).substring(4, beaconDetectionsAsString.get(0).length() - 4);

        this.beaconDetections = beaconDetectionsAsString.stream()
                .skip(1)
                .map(BeaconDetection::new)
                .collect(toList());
    }

    public List<BeaconDetection> getBeaconDetections() {
        return beaconDetections;
    }

    public String toString() {
        return name;
    }
}
