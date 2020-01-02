package com.coditory.sandbox.dynamic;

import java.util.HashMap;
import java.util.Map;

public class Knapsack {
    /**
     * Returns maximum value of items in optimal knapsack
     */
    public int knapsack(int[] weights, int[] values, int capacity) {
        if (capacity < 0) return 0;
        if (weights == null || values == null) return 0;
        if (weights.length == 0 || values.length == 0) return 0;
        if (weights.length != values.length) return 0;
        // Time complexity
        // without cache: exponential O(2^n)
        // with cache: O(nm), where m is capacity
        Map<String, Integer> cache = new HashMap<>();
        return knapsack(weights, values, weights.length - 1, capacity, cache);
    }

    private int knapsack(
            int[] weights, int[] values,
            int i, int capacity,
            Map<String, Integer> cache
    ) {
        if (capacity <= 0 || i < 0) return 0;
        String key = capacity + ":" + i;
        if (cache.containsKey(key)) return cache.get(key);
        int withoutItem = knapsack(weights, values, i - 1, capacity, cache);
        int result = withoutItem;
        if (weights[i] <= capacity) {
            int withItem = knapsack(weights, values, i - 1, capacity - weights[i], cache) + values[i];
            result = Math.max(withItem, withoutItem);
        }
        cache.put(key, result);
        return result;
    }
}

