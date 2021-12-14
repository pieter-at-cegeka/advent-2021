package be.acerta.pieter.advent2021.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class PolymerCharacterOccurrencePredictor {
    private final Map<ElementPair, PairInsertionRule> pairInsertionRulesByElementPair;

    public PolymerCharacterOccurrencePredictor(List<PairInsertionRule> pairInsertionRules) {
        this.pairInsertionRulesByElementPair = pairInsertionRules.stream()
                .collect(toMap(
                        PairInsertionRule::getOriginalElementPair,
                        identity()
                ));
    }

    public Map<Character, Long> calculateOccurrencesPerCharacterAfterIterations(Polymer polymer, int iterations) {
        List<ElementPair> elementPairs = polymer.asElementPairs();

        Stream<Map<Character, Long>> listOfOccurrencesPerCharacter = elementPairs.stream()
                .map(elementPair -> getOccurrencesPerCharacterAfterIterations(elementPair, iterations));

        Stream<Map<Character, Long>> compensatorsForDuplicateCharacters = elementPairs.stream().skip(1)
                .map(elementPair -> Map.of(elementPair.getFirstElement(), -1L));

        return sum(Stream.concat(listOfOccurrencesPerCharacter, compensatorsForDuplicateCharacters).collect(toList()));
    }

    private final Map<ElementPair, Map<Integer, Map<Character, Long>>> cachedOccurrencesPerCharacterAfterIterations = new HashMap<>();

    private Map<Character, Long> getOccurrencesPerCharacterAfterIterations(ElementPair elementPair, int iterations) {
        if (!cachedOccurrencesPerCharacterAfterIterations.containsKey(elementPair)) {
            cachedOccurrencesPerCharacterAfterIterations.put(elementPair, new HashMap<>());
        }

        Map<Integer, Map<Character, Long>> occurrencesAfterIteration = cachedOccurrencesPerCharacterAfterIterations.get(elementPair);
        if (!occurrencesAfterIteration.containsKey(iterations)) {
            occurrencesAfterIteration.put(iterations, calculateOccurrencesPerCharacterAfterIterations(elementPair, iterations));
        }
        return occurrencesAfterIteration.get(iterations);
    }

    private Map<Character, Long> calculateOccurrencesPerCharacterAfterIterations(ElementPair elementPair, int iterations) {
        if (iterations == 0) {
            if (elementPair.getFirstElement() == elementPair.getSecondElement()) {
                return Map.of(elementPair.getFirstElement(), 2L);
            } else {
                return Map.of(elementPair.getFirstElement(), 1L, elementPair.getSecondElement(), 1L);
            }
        } else {
            PairInsertionRule pairInsertionRule = pairInsertionRulesByElementPair.get(elementPair);

            return sum(List.of(
                    getOccurrencesPerCharacterAfterIterations(pairInsertionRule.getFirstResultingElementPair(), iterations - 1),
                    getOccurrencesPerCharacterAfterIterations(pairInsertionRule.getSecondResultingElementPair(), iterations - 1),
                    Map.of(pairInsertionRule.getInsertedCharacter(), -1L)));
        }
    }

    private Map<Character, Long> sum(List<Map<Character, Long>> listOfOccurrencesPerCharacter) {
        Map<Character, Long> sum = new HashMap<>();
        listOfOccurrencesPerCharacter
                .forEach(occurrencesPerCharacter -> occurrencesPerCharacter
                        .forEach((character, count) -> {
                            Long previousCount = sum.getOrDefault(character, 0L);
                            sum.put(character, previousCount + count);
                        }));

        return sum;
    }
}
