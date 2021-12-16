package be.acerta.pieter.advent2021.day16;

import java.util.List;

import static java.lang.String.format;

public abstract class Packet {
    private final int version;

    public Packet(int version) {
        this.version = version;
    }

    public String getVersionSummary() {
        return format("version %s and versionsum %s", version, calculateVersionSum());
    }

    private int calculateVersionSum() {
        return getSubPackets().stream()
                .mapToInt(Packet::calculateVersionSum)
                .sum() + version;
    }

    public abstract long getValue();

    public abstract List<Packet> getSubPackets();
}
