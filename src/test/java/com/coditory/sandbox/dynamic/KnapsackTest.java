package com.coditory.sandbox.dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnapsackTest {
    Knapsack s = new Knapsack();

    @Test
    void shouldOptimizeKnapsack() {
        int[] weights = new int[]{1, 2, 3, 2, 5};
        int[] values = new int[]{5, 3, 5, 3, 2};
        assertEquals(16, knapsack(weights, values, 10));
    }

    private int knapsack(int[] weights, int[] values, int capacity) {
        return s.knapsack(weights, values, capacity);
    }
}