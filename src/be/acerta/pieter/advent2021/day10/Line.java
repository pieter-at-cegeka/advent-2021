package be.acerta.pieter.advent2021.day10;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static be.acerta.pieter.advent2021.day10.Chunk.chunksOf;

public class Line {
    private final List<Chunk> chunks;

    public Line(String symbolsAsString) {
        chunks = chunksOf(symbolsAsString.chars()
                .mapToObj(characterAsInt -> (char) characterAsInt)
                .map(Symbol::ofCharacter)
                .collect(Collectors.toList()));
    }

    public boolean isComplete() {
        return chunks.stream()
                .noneMatch(Chunk::isIncomplete);
    }

    public Optional<Integer> getSyntaxErrorScore() {
        return chunks.stream()
                .map(Chunk::getSyntaxErrorScore)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    private boolean isCorrupt() {
        return getSyntaxErrorScore().isPresent();
    }

    public Optional<Long> getCompletionScore() {
        if (isCorrupt()) {
            return Optional.empty();
        }
        return Optional.of(chunks.get(chunks.size() - 1).getCompletionScore());
    }
}
