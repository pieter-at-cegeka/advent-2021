package be.acerta.pieter.advent2021.day16;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class PacketParser {
    private final String hexadecimalRepresentation;
    private final String binaryRepresentation;

    private int currentIndex;

    public PacketParser(String hexadecimalRepresentation) {
        this.hexadecimalRepresentation = hexadecimalRepresentation;
        this.binaryRepresentation = toBinaryRepresentation(hexadecimalRepresentation);
    }

    private static String toBinaryRepresentation(String hexadecimalRepresentation) {
        StringBuffer binaryRepresentation = new StringBuffer();

        for (char hexadecimalCharacter : hexadecimalRepresentation.toCharArray()) {
            binaryRepresentation.append(toBinaryRepresentation(hexadecimalCharacter));
        }

        return binaryRepresentation.toString();
    }

    private static String toBinaryRepresentation(char hexadecimalCharacter) {
        switch (hexadecimalCharacter) {
            case '0': return "0000";
            case '1': return "0001";
            case '2': return "0010";
            case '3': return "0011";
            case '4': return "0100";
            case '5': return "0101";
            case '6': return "0110";
            case '7': return "0111";
            case '8': return "1000";
            case '9': return "1001";
            case 'A': return "1010";
            case 'B': return "1011";
            case 'C': return "1100";
            case 'D': return "1101";
            case 'E': return "1110";
            case 'F': return "1111";
        }

        throw new IllegalArgumentException("" + hexadecimalCharacter);
    }

    public String getHexadecimalRepresentation() {
        return hexadecimalRepresentation;
    }

    public String getBinaryRepresentation() {
        return binaryRepresentation;
    }

    public Packet parsePacket() {
        currentIndex = 0;
        return parseNextPacket();
    }

    private Packet parseNextPacket() {
        int version = readNextInt(3);
        int typeAsInt = readNextInt(3);

        switch (typeAsInt) {
            case 4: return parseLiteralPacket(version);
            default: return parseOperatorPacket(version, typeAsInt);
        }
    }

    private LiteralPacket parseLiteralPacket(int version) {
        boolean thereAreMorePartsOfValue = true;
        StringBuffer valueAsBinaryString = new StringBuffer();
        while (thereAreMorePartsOfValue) {
            thereAreMorePartsOfValue = readNextBit();
            valueAsBinaryString.append(readNextString(4));
        }
        return new LiteralPacket(version, Long.parseLong(valueAsBinaryString.toString(), 2));
    }

    private OperatorPacket parseOperatorPacket(int version, int typeAsInt) {
        boolean packetLengthTypeIsByNumberOfSubPackets = readNextBit();
        List<Packet> subPackets;
        if (packetLengthTypeIsByNumberOfSubPackets) {
            int numberOfSubPackets = readNextInt(11);
            subPackets = parseNumberOfPackets(numberOfSubPackets);
        } else {
            int numberOfBitsInSubPackets = readNextInt(15);
            subPackets = parsePacketsInNextBits(numberOfBitsInSubPackets);
        }

        return new OperatorPacket(version, typeAsInt, subPackets);
    }

    private List<Packet> parseNumberOfPackets(int numberOfSubPackets) {
        List<Packet> packets = new ArrayList<>();
        for (int index = 0; index < numberOfSubPackets; index++) {
            packets.add(parseNextPacket());
        }
        return packets;
    }

    private List<Packet> parsePacketsInNextBits(int numberOfBitsInSubPackets) {
        int indexToStopAt = currentIndex + numberOfBitsInSubPackets;
        List<Packet> packets = new ArrayList<>();
        while (currentIndex < indexToStopAt) {
            packets.add(parseNextPacket());
        }
        return packets;
    }

    public int readNextInt(int numberOfBits) {
        String integerAsBinaryString = binaryRepresentation.substring(currentIndex, currentIndex += numberOfBits);
        return Integer.parseInt(integerAsBinaryString, 2);
    }

    private String readNextString(int numberOfBits) {
        return binaryRepresentation.substring(currentIndex, currentIndex += numberOfBits);
    }

    public boolean readNextBit() {
        return binaryRepresentation.charAt(currentIndex++) == '1';
    }
}
