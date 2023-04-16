package org.example.taskthird;

import java.util.Arrays;
import org.junit.jupiter.api.*;
import static org.example.taskthird.DivisFind.divisFind;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SuchDivisorTest {
    private static int[] expected = {1, 2, 3, 4, 6, 12};
    private static int[] actual;

    @BeforeAll
    public static void init() {
        actual = divisFind(12);
    }
    @Test
    public void searchDivisorsTest() {
        assertArrayEquals(expected,actual);
    }
    @AfterAll
    public static void done() {
        System.out.println("Tests finished\n" +
                "Expected " + Arrays.toString(expected) +
                "\nActual " + Arrays.toString(actual));
    }
}