package be.acerta.pieter.advent2021.day12;

import java.util.ArrayList;
import java.util.List;

import static be.acerta.pieter.advent2021.day12.Cave.caveForName;

public class Connection {
    private final List<Cave> caves;

    public Connection(String connectionAsString) {
        String[] cavesAsString = connectionAsString.split("-");
        caves = new ArrayList<>(2);
        caves.add(caveForName(cavesAsString[0]));
        caves.add(caveForName(cavesAsString[1]));

        caves.get(0).addConnection(this);
        caves.get(1).addConnection(this);
    }

    public List<Cave> getCaves() {
        return caves;
    }

    public Cave findOtherCave(Cave cave) {
        if (caves.get(0).equals(cave)) {
            return caves.get(1);
        } else {
            return caves.get(0);
        }
    }

    public String toString() {
        return caves.get(0).toString() + "-" + caves.get(1).toString();
    }
}
