package be.acerta.pieter.advent2021.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public class Chunk {
    private final Symbol openingSymbol;
    private final List<Chunk> children;
    private final Optional<Symbol> closingSymbol;

    public Chunk(List<Symbol> symbols) {
        openingSymbol = symbols.get(0);

        Optional<Integer> indexOfClosingSymbol = findIndexOfClosingSymbol(symbols);
        if (indexOfClosingSymbol.isPresent()) {
            closingSymbol = Optional.of(symbols.get(indexOfClosingSymbol.get()));
            children = chunksOf(symbols.subList(1, indexOfClosingSymbol.get()));
        } else {
            closingSymbol = Optional.empty();
            children = chunksOf(symbols.subList(1, symbols.size()));
        }
    }

    public static List<Chunk> chunksOf(List<Symbol> symbols) {
        if (symbols.isEmpty()) {
            return emptyList();
        } else {
            List<Chunk> siblings = new ArrayList<>();
            Optional<Integer> indexOfClosingSymbol = findIndexOfClosingSymbol(symbols);
            if (indexOfClosingSymbol.isPresent()) {
                siblings.add(new Chunk(symbols.subList(0, indexOfClosingSymbol.get() + 1)));
                boolean thereAreMoreSiblings = indexOfClosingSymbol.get() < symbols.size() - 1;
                if (thereAreMoreSiblings) {
                    siblings.addAll(chunksOf(symbols.subList(indexOfClosingSymbol.get() + 1, symbols.size())));
                }
            } else {
                siblings.add(new Chunk(symbols.subList(0, symbols.size())));
            }
            return siblings;
        }
    }

    private static Optional<Integer> findIndexOfClosingSymbol(List<Symbol> symbols) {
        int index = 0;
        int currentDepth = 1;

        while (currentDepth > 0) {
            index += 1;

            if (index == symbols.size()) {
                return Optional.empty();
            }

            currentDepth += symbols.get(index).opens() ? 1 : -1;

            if (currentDepth == 0) {
                return Optional.of(index);
            }
        }

        throw new IllegalStateException();
    }

    public boolean isIncomplete() {
        return closingSymbol.isEmpty();
    }

    public Optional<Integer> getSyntaxErrorScore() {
        Optional<Integer> syntaxErrorScoreOfChildren = children.stream()
                .map(Chunk::getSyntaxErrorScore)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        if (syntaxErrorScoreOfChildren.isPresent()) {
            return syntaxErrorScoreOfChildren;
        } else {
            if (closingSymbol.isEmpty()) {
                return Optional.empty();
            } else {
                if (openingSymbol.matches(closingSymbol.orElseThrow())) {
                    return Optional.empty();
                } else {
                    return Optional.of(closingSymbol.orElseThrow().getSyntaxErrorScore());
                }
            }
        }
    }

    public long getCompletionScore() {
        if (closingSymbol.isPresent()) {
            return 0L;
        }

        long previousCompletionScore = children.isEmpty() ? 0L : children.get(children.size() - 1).getCompletionScore();
        return previousCompletionScore * 5L + openingSymbol.getCompletionScore();
    }
}
