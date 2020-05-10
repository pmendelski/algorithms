package com.coditory.sandbox.dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxSquareSpec {
    MaxSquare s = new MaxSquare();

    @Test
    void shouldFindMaxSquareSize() {
        int[][] values = new int[][]{
                new int[]{1, 1, 0, 1, 1, 1, 0, 0},
                new int[]{1, 0, 1, 1, 1, 1, 1, 1},
                new int[]{0, 1, 1, 1, 1, 1, 1, 1},
                new int[]{0, 1, 1, 1, 0, 1, 1, 1}
        };
        assertEquals(3, maxSquare(values));
    }

    private int maxSquare(int[][] values) {
        return s.maxSquare(values);
    }
}