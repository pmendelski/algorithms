package com.coditory.sandbox.array;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchSpec {
    @Test
    void shouldFindElement() {
        assertEquals(5, findExact(asList(-2, 0, 2, 4, 5, 8, 9), 8));
    }

    @Test
    void shouldFindFirstElement() {
        assertEquals(0, findExact(asList(-2, 0, 2, 4, 5, 8, 9), -2));
    }

    @Test
    void shouldFindLastElement() {
        assertEquals(6, findExact(asList(-2, 0, 2, 4, 5, 8, 9), 9));
    }

    @Test
    void shouldNotFindNonExistingElement() {
        assertEquals(-1, findExact(asList(-2, 0, 2, 4, 5, 8, 9), 10));
    }

    @Test
    void shouldFindFirstElementOrSmaller() {
        assertEquals(2, findExactOrSmaller(asList(-2, 0, 2, 4, 5, 8, 9), 3));
    }

    @Test
    void shouldFindLastElementOrBigger() {
        assertEquals(3, findExactOrBigger(asList(-2, 0, 2, 4, 5, 8, 9), 3));
    }

    private int findExact(List<Integer> values, int value) {
        int[] ints = values.stream().mapToInt(Integer::intValue).toArray();
        return BinarySearch.findExact(ints, value);
    }

    private int findExactOrSmaller(List<Integer> values, int value) {
        int[] ints = values.stream().mapToInt(Integer::intValue).toArray();
        return BinarySearch.findExactOrSmaller(ints, value);
    }

    private int findExactOrBigger(List<Integer> values, int value) {
        int[] ints = values.stream().mapToInt(Integer::intValue).toArray();
        return BinarySearch.findExactOrBigger(ints, value);
    }
}
