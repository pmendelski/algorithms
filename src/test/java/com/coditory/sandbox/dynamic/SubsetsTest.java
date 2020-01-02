package com.coditory.sandbox.dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubsetsTest {
    Subsets subsets = new Subsets();

    @Test
    void shouldCountSubsetsThatSumToN() {
        assertEquals(1, subsets(new int[]{1, 2, 3}, 0));
        assertEquals(0, subsets(new int[]{1, 2, 3}, -1));
        assertEquals(2, subsets(new int[]{2, 4, 6, 10}, 16));
    }

    private int subsets(int[] values, int sum) {
        return subsets.subsets(values, sum);
    }
}