package be.acerta.pieter.advent2021.day14;

public class PairInsertionRule {
    private final char firstOriginalCharacter;
    private final char secondOriginalCharacter;
    private final char insertedCharacter;

    private final ElementPair originalElementPair;
    private final ElementPair firstResultingElementPair;
    private final ElementPair secondResultingElementPair;

    public PairInsertionRule(String pairInsertionRuleAsString) {
        firstOriginalCharacter = pairInsertionRuleAsString.charAt(0);
        secondOriginalCharacter = pairInsertionRuleAsString.charAt(1);
        insertedCharacter = pairInsertionRuleAsString.charAt(6);

        originalElementPair = ElementPair.of(firstOriginalCharacter, secondOriginalCharacter);
        firstResultingElementPair = ElementPair.of(firstOriginalCharacter, insertedCharacter);
        secondResultingElementPair = ElementPair.of(insertedCharacter, secondOriginalCharacter);
    }

    public ElementPair getOriginalElementPair() {
        return originalElementPair;
    }

    public ElementPair getFirstResultingElementPair() {
        return firstResultingElementPair;
    }

    public ElementPair getSecondResultingElementPair() {
        return secondResultingElementPair;
    }

    public char getInsertedCharacter() {
        return insertedCharacter;
    }

    @Override
    public String toString() {
        return "" + firstOriginalCharacter + secondOriginalCharacter + " -> " + insertedCharacter;
    }
}
