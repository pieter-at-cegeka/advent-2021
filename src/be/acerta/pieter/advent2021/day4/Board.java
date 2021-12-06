package be.acerta.pieter.advent2021.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Board {
    private final int index;
    private final Map<Integer, Entry> entriesByNumber;
    private final List<Line> lines;

    public Board(List<String> boardAsStrings, int index) {
        this.index = index;
        List<Entry> entries = new ArrayList<>(25);
        lines = new ArrayList<>(10);

        for (int y = 0; y < 5; y++) {
            String[] lineAsStrings = boardAsStrings.get(y).stripLeading()
                    .split(" +");

            int i = 1;
            for (int x = 0; x < 5; x++) {
                entries.add(new Entry(Integer.parseInt(lineAsStrings[x])));
            }
        }

        lines.add(new Line(entries.subList(0, 5)));
        lines.add(new Line(entries.subList(5, 10)));
        lines.add(new Line(entries.subList(10, 15)));
        lines.add(new Line(entries.subList(15, 20)));
        lines.add(new Line(entries.subList(20, 25)));

        lines.add(new Line(Stream.of(0, 5, 10, 15, 20).map(entries::get).collect(toList())));
        lines.add(new Line(Stream.of(1, 6, 11, 16, 21).map(entries::get).collect(toList())));
        lines.add(new Line(Stream.of(2, 7, 12, 17, 22).map(entries::get).collect(toList())));
        lines.add(new Line(Stream.of(3, 8, 13, 18, 23).map(entries::get).collect(toList())));
        lines.add(new Line(Stream.of(4, 9, 14, 19, 24).map(entries::get).collect(toList())));

        entriesByNumber = entries.stream()
                .collect(toMap(
                        entry -> entry.number,
                        identity()));
    }

    public int getIndex() {
        return index;
    }

    public boolean markNumber(int drawnNumber) {
        Entry entry = entriesByNumber.get(drawnNumber);

        if (entry != null) {
            entry.mark();

            return lines.stream()
                    .anyMatch(Line::fullyMarked);
        }

        return false;
    }

    public int calculateScore() {
        return entriesByNumber.values().stream()
                .filter(Entry::unmarked)
                .mapToInt(Entry::getNumber)
                .sum();
    }

    private static class Line {
        private final List<Entry> entries;

        private Line(List<Entry> entries) {
            this.entries = entries;
        }

        public boolean fullyMarked() {
            return entries.stream().noneMatch(Entry::unmarked);
        }

        @Override
        public String toString() {
            return entries.toString();
        }
    }

    private static class Entry {
        private final int number;
        private boolean marked;

        public Entry(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public void mark() {
            this.marked = true;
        }

        public boolean unmarked() {
            return !marked;
        }

        @Override
        public String toString() {
            return (marked ? "+" : "-") + number;
        }
    }
}
