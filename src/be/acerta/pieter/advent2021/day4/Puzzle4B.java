package be.acerta.pieter.advent2021.day4;

import be.acerta.pieter.advent2021.InputFileReadingUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Puzzle4B {
    public static void main(String... args) {
        List<String> inputLines = InputFileReadingUtil.readFileLines("puzzle4_input.txt");

        List<Board> boards = new ArrayList<>();
        int index = 0;
        while (2 + (index * 6) < inputLines.size()) {
            boards.add(new Board(inputLines.subList(2 + (index * 6), 7 + (index * 6)), index));
            index++;
        }

        List<Integer> drawnNumbers = stream(inputLines.get(0).split(","))
                .map(Integer::parseInt)
                .collect(toList());

        AtomicReference<Integer> drawnNumber = new AtomicReference<>();
        Set<Board> finishedBoards = new HashSet<>();
        AtomicReference<Board> winningBoard = new AtomicReference<>();
        index = 0;
        while (winningBoard.get() == null) {
            drawnNumber.set(drawnNumbers.get(index));
            boards.forEach(board -> {
                boolean boardHasFinished = board.markNumber(drawnNumber.get());
                if (boardHasFinished) {
                    finishedBoards.add(board);
                    if (finishedBoards.size() == boards.size() && winningBoard.get() == null) {
                        winningBoard.set(board);
                    }
                }
            });
            index++;
        }

        int indexOfWinningBoard = winningBoard.get().getIndex();
        int lastDrawnNumber = drawnNumber.get();
        int score = winningBoard.get().calculateScore();
        int solution = score * lastDrawnNumber;

        System.out.println(String.format("Board %s will win last after drawing number %s, score is %s, solution is %s",
                indexOfWinningBoard,
                lastDrawnNumber,
                score,
                solution));
    }
}
