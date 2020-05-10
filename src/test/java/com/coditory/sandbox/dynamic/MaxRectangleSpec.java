package com.coditory.sandbox.dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxRectangleSpec {
    MaxRectangle s = new MaxRectangle();

    @Test
    void shouldFindMaxRectangle() {
        assertEquals(9, maxRect(new int[]{2, 3, 4, 3, 0, 6}));
    }

    private int maxRect(int[] histogram) {
        return s.maxRect(histogram);
    }
}