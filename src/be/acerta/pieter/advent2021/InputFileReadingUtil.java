package be.acerta.pieter.advent2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputFileReadingUtil {
    public static List<String> readFileLines(String fileName) {
        try {
            return Files.readAllLines(Path.of("files", fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
