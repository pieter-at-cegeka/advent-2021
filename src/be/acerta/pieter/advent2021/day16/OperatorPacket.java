package be.acerta.pieter.advent2021.day16;

import java.util.List;

import static java.lang.String.format;

public class OperatorPacket extends Packet {

    private final int typeAsInt;
    private final List<Packet> subPackets;

    public OperatorPacket(int version, int typeAsInt, List<Packet> subPackets) {
        super(version);

        this.typeAsInt = typeAsInt;
        this.subPackets = subPackets;
    }

    public String toString() {
        return format("Operator packet of %s, type %s and %s subpackets with value %s", getVersionSummary(), typeAsInt, subPackets.size(), getValue());
    }

    @Override
    public List<Packet> getSubPackets() {
        return subPackets;
    }

    @Override
    public long getValue() {
        switch (typeAsInt) {
            case 0:
                return getSubPackets().stream()
                        .mapToLong(Packet::getValue)
                        .sum();
            case 1:
                return getSubPackets().stream()
                        .mapToLong(Packet::getValue)
                        .reduce(1L, (product, multiplicand) -> product * multiplicand);
            case 2:
                return getSubPackets().stream()
                        .mapToLong(Packet::getValue)
                        .min().orElseThrow();
            case 3:
                return getSubPackets().stream()
                        .mapToLong(Packet::getValue)
                        .max().orElseThrow();
            case 5:
                return getSubPackets().get(0).getValue() > getSubPackets().get(1).getValue() ? 1L : 0L;
            case 6:
                return getSubPackets().get(0).getValue() < getSubPackets().get(1).getValue() ? 1L : 0L;
            case 7:
                return getSubPackets().get(0).getValue() == getSubPackets().get(1).getValue() ? 1L : 0L;
        }

        throw new IllegalArgumentException("" + typeAsInt);
    }
}
