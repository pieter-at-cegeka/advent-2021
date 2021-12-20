package be.acerta.pieter.advent2021.day19;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.ArrayList;
import java.util.List;

import static be.acerta.pieter.advent2021.day19.Orientation.ALL_ORIENTATIONS;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class Puzzle19 {
    public static void main(String... args) {
        List<ScannerOutput> scannerOutputs = splitIntoScannerOutputs(InputFileReadingUtil.readFileLines("puzzle19_input.txt")).stream()
                .map(ScannerOutput::new)
                .collect(toList());

        Space space = new Space();
        space.addScanner(new Location(0, 0, 0), ALL_ORIENTATIONS.get(0), scannerOutputs.get(0));
        scannerOutputs.remove(0);

        while (!scannerOutputs.isEmpty()) {
            for (int i = 0; i < scannerOutputs.size(); i++) {
                if (space.locateScanner(scannerOutputs.get(i))) {
                    scannerOutputs.remove(i);
                }
            }
        }

        int numberOfBeacons = space.getAllBeaconLocations().size();

        int maximumManhattanDistanceBetweenTwoScanners = 0;
        for (Scanner oneScanner : space.getScanners()) {
            for (Scanner otherScanner : space.getScanners()) {
                if (oneScanner != otherScanner) {
                    maximumManhattanDistanceBetweenTwoScanners = Math.max(maximumManhattanDistanceBetweenTwoScanners, oneScanner.getLocation().manhattanDistance(otherScanner.getLocation()));
                }
            }
        }

        System.out.println(format("There are %s beacons and the largest manhattan distance between two scanners is %s", numberOfBeacons, maximumManhattanDistanceBetweenTwoScanners));
    }

    private static List<List<String>> splitIntoScannerOutputs(List<String> inputLines) {
        List<List<String>> scannerOutputs = new ArrayList<>();

        int startOfNextScannerOutput = 0;
        for (int index = 0; index < inputLines.size(); index++) {
            if (inputLines.get(index).equals("")) {
                scannerOutputs.add(inputLines.subList(startOfNextScannerOutput, index));
                startOfNextScannerOutput = index + 1;
            }
        }

        scannerOutputs.add(inputLines.subList(startOfNextScannerOutput, inputLines.size()));
        return scannerOutputs;
    }
}
