package org.example.taskthird;

import java.util.stream.IntStream;


public class DivisFind {
    public static int[] divisFind(int n) {
        return IntStream.rangeClosed(1, n)
                .filter(i -> n % i == 0)
                .toArray();
    }
}

