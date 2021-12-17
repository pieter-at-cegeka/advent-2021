package be.acerta.pieter.advent2021.day17;

import java.util.List;

import static java.lang.String.format;

public class Puzzle17 {
    public static void main(String... args) {
        TargetArea testTargetArea = new TargetArea(20, 30, -10, -5);
        TargetArea targetArea = new TargetArea(56, 76, -162, -134);

        List<Shot> shotsHittingTestTargetArea = testTargetArea.calculateShotsThatHit();
        int numberOfShotsThatHitTestArea = shotsHittingTestTargetArea.size();
        int maximumHeightOfShotsHittingTestArea = shotsHittingTestTargetArea.stream()
                .mapToInt(Shot::getMaxHeight)
                .max().orElseThrow();

        List<Shot> shotsHittingTargetArea = targetArea.calculateShotsThatHit();
        int numberOfShotsThatHitArea = shotsHittingTargetArea.size();
        int maximumHeightOfShotsHittingArea = shotsHittingTargetArea.stream()
                .mapToInt(Shot::getMaxHeight)
                .max().orElseThrow();

        System.out.println(format("There are %s shots hitting the test area, with a maximum height of %s",
                numberOfShotsThatHitTestArea,
                maximumHeightOfShotsHittingTestArea));
        System.out.println(format("There are %s shots hitting the real area, with a maximum height of %s",
                numberOfShotsThatHitArea,
                maximumHeightOfShotsHittingArea));
    }
}
