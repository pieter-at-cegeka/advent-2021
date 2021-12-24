package be.acerta.pieter.advent2021.day21;

import static java.lang.String.format;

public class Puzzle21A {
    public static void main(String... args) {
        runWithStartingPositions(4, 8);
        runWithStartingPositions(7, 6);
    }

    private static void runWithStartingPositions(int startingPositionOfPlayer1, int startingPositionOfPlayer2) {
        int currentPositionOfPlayer1 = startingPositionOfPlayer1;
        int scoreOfPlayer1 = 0;

        int currentPositionOfPlayer2 = startingPositionOfPlayer2;
        int scoreOfPlayer2 = 0;

        int numberOfDieRolls = 0;
        int nextDieRoll = 1;

        boolean player1IsNext = true;

        while(scoreOfPlayer1 < 1000 && scoreOfPlayer2 < 1000) {
            int sumOfDieRolls = nextDieRoll++ + nextDieRoll++ + nextDieRoll++;
            if (nextDieRoll > 100) {
                nextDieRoll -= 100;
            }
            numberOfDieRolls += 3;

            if (player1IsNext) {
                currentPositionOfPlayer1 += sumOfDieRolls;
                while (currentPositionOfPlayer1 > 10) {
                    currentPositionOfPlayer1 -= 10;
                }
                scoreOfPlayer1 += currentPositionOfPlayer1;
            } else {
                currentPositionOfPlayer2 += sumOfDieRolls;
                while (currentPositionOfPlayer2 > 10) {
                    currentPositionOfPlayer2 -= 10;
                }
                scoreOfPlayer2 += currentPositionOfPlayer2;
            }

            player1IsNext = !player1IsNext;
        }

        System.out.println(format("With starting positions %s and %s, final score of player 1 is %s, of player 2 is %s, number of die rolls is %s and the product of the number of die rolls with the losing score is %s",
                startingPositionOfPlayer1,
                startingPositionOfPlayer2,
                scoreOfPlayer1,
                scoreOfPlayer2,
                numberOfDieRolls,
                numberOfDieRolls * (player1IsNext ? scoreOfPlayer1 : scoreOfPlayer2)));
    }
}
