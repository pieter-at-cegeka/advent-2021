package be.acerta.pieter.advent2021.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Cave {
    private final String name;
    private final boolean isStart;
    private final boolean isEnd;
    private final boolean isSmall;

    private final List<Connection> connections = new ArrayList<>();

    private static final Map<String, Cave> cavesByName = new HashMap<>();

    public static Cave caveForName(String name) {
        if (!cavesByName.containsKey(name)) {
            cavesByName.put(name, new Cave(name));
        }

        return cavesByName.get(name);
    }

    public Cave(String name) {
        this.name = name;

        if ("start".equals(name)) {
            isStart = true;
            isEnd = false;
            isSmall = true;
        } else if ("end".equals(name)) {
            isStart = false;
            isEnd = true;
            isSmall = true;
        } else {
            isStart = false;
            isEnd = false;
            isSmall = Character.isLowerCase(name.charAt(0));
        }
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean isSmall() {
        return isSmall;
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public List<Cave> findNeighbours() {
        return connections.stream()
                .map(connection -> connection.findOtherCave(this))
                .collect(toList());
    }

    public String toString() {
        return name;
    }
}
