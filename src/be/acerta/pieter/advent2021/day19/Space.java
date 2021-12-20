package be.acerta.pieter.advent2021.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static be.acerta.pieter.advent2021.day19.Orientation.ALL_ORIENTATIONS;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Space {
    private final List<Scanner> scanners = new ArrayList<>();

    public List<Scanner> getScanners() {
        return scanners;
    }

    public void addScanner(Location location, Orientation orientation, ScannerOutput scannerOutput) {
        this.scanners.add(new Scanner(location, orientation, scannerOutput));
    }

    public boolean locateScanner(ScannerOutput scannerOutput) {
        for (Orientation possibleNewScannerOrientation : ALL_ORIENTATIONS) {
            List<BeaconDetection> translatedBeaconDetections = scannerOutput.getBeaconDetections().stream()
                    .map(possibleNewScannerOrientation::translate)
                    .collect(toList());

            for (Scanner scanner : scanners) {
                Set<Location> beaconLocations = scanner.getBeaconLocations();

                for (Location beaconLocation : beaconLocations) {
                    for (int j = 0; j < translatedBeaconDetections.size() - 11; j++) {
                        Location possibleNewScannerLocation = beaconLocation.reverseTranspose(translatedBeaconDetections.get(j));

                        if (translatedBeaconDetections.stream()
                                .map(possibleNewScannerLocation::transpose)
                                .filter(beaconLocations::contains)
                                .count() >= 12) {
                            scanners.add(new Scanner(possibleNewScannerLocation, possibleNewScannerOrientation, scannerOutput));
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public Set<Location> getAllBeaconLocations() {
        return scanners.stream()
                .map(Scanner::getBeaconLocations)
                .flatMap(Set::stream)
                .collect(toSet());
    }
}
