package be.acerta.pieter.advent2021.day16;

import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.emptyList;

public class LiteralPacket extends Packet {
    private final long literalValue;

    public LiteralPacket(int version, long literalValue) {
        super(version);
        this.literalValue = literalValue;
    }

    public String toString() {
        return format("Literal packet of %s with value %s", getVersionSummary(), getValue());
    }

    @Override
    public List<Packet> getSubPackets() {
        return emptyList();
    }

    @Override
    public long getValue() {
        return literalValue;
    }
}
