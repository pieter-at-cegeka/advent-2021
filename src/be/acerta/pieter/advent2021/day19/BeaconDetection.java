package be.acerta.pieter.advent2021.day19;

public class BeaconDetection {
    private final int leftValue;
    private final int middleValue;
    private final int rightValue;

    public BeaconDetection(String beaconDetectionAsString) {
        String[] values = beaconDetectionAsString.split(",");

        leftValue = Integer.parseInt(values[0]);
        middleValue = Integer.parseInt(values[1]);
        rightValue = Integer.parseInt(values[2]);
    }

    public BeaconDetection(int leftValue, int middleValue, int rightValue) {
        this.leftValue = leftValue;
        this.middleValue = middleValue;
        this.rightValue = rightValue;
    }

    public int getLeftValue() {
        return leftValue;
    }

    public int getMiddleValue() {
        return middleValue;
    }

    public int getRightValue() {
        return rightValue;
    }
}
