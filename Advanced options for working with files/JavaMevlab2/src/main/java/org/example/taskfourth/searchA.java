package org.example.taskfourth;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;


public class searchA {
    public static void search_A() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("FindAin.txt"))) {
            Stream<String> stream = reader.lines().sorted(Comparator.comparing(String::length)).
                    filter(s -> s.contains("a"));
            Files.write(Paths.get("FindAout.txt"), stream.toList());
        }
    }
    public static void main(String[] args) throws IOException {
        search_A();
    }
}
