package com.coditory.sandbox.array;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

abstract public class BinarySearchSpec {
    private final BiFunction<List<Integer>, Integer, Integer> binarySearch;

    public BinarySearchSpec(BiFunction<List<Integer>, Integer, Integer> binarySearch) {
        this.binarySearch = binarySearch;
    }

    @Test
    void shouldFindElement() {
        assertEquals(5, find(asList(-2, 0, 2, 4, 5, 8, 9), 8));
    }

    @Test
    void shouldFindFirstElementInDuplicates() {
        assertEquals(5, find(asList(-2, 0, 2, 4, 5, 8, 8, 8, 9), 8));
        assertEquals(11, find(asList(-2, 0, 2, 4, 5, 5, 5, 5, 5, 5, 5, 8, 8, 8, 9), 8));
    }

    @Test
    void shouldFindFirstElementInDuplicatesOnly() {
        assertEquals(0, find(asList(5, 5, 5, 5, 5, 5, 5), 5));
    }

    @Test
    void shouldFindFirstElement() {
        assertEquals(0, find(asList(-2, 0, 2, 4, 5, 8, 9), -2));
    }

    @Test
    void shouldFindLastElement() {
        assertEquals(6, find(asList(-2, 0, 2, 4, 5, 8, 9), 9));
    }


    protected int find(List<Integer> values, int value) {
        return binarySearch.apply(values, value);
    }
}

class BinarySearchExactSpec extends BinarySearchSpec {
    public BinarySearchExactSpec() {
        super((List<Integer> values, Integer value) -> {
            int[] ints = values.stream().mapToInt(Integer::intValue).toArray();
            return BinarySearch.findExact(ints, value);
        });
    }

    @Test
    void shouldNotFindNonExistingElement() {
        assertEquals(-1, find(asList(-2, 0, 2, 4, 5, 8, 9), 10));
    }
}

class BinarySearchExactOrSmallerSpec extends BinarySearchSpec {
    public BinarySearchExactOrSmallerSpec() {
        super((List<Integer> values, Integer value) -> {
            int[] ints = values.stream().mapToInt(Integer::intValue).toArray();
            return BinarySearch.findExactOrSmaller(ints, value);
        });
    }

    @Test
    void shouldFindFirstElementOrSmaller() {
        assertEquals(2, find(asList(-2, 0, 2, 4, 5, 8, 9), 3));
    }
}

class BinarySearchExactOrBiggerSpec extends BinarySearchSpec {
    public BinarySearchExactOrBiggerSpec() {
        super((List<Integer> values, Integer value) -> {
            int[] ints = values.stream().mapToInt(Integer::intValue).toArray();
            return BinarySearch.findExactOrBigger(ints, value);
        });
    }

    @Test
    void shouldFindLastElementOrBigger() {
        assertEquals(3, find(asList(-2, 0, 2, 4, 5, 8, 9), 3));
    }
}