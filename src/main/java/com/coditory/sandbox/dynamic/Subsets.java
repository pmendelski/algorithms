package com.coditory.sandbox.dynamic;

import java.util.HashMap;
import java.util.Map;

public class Subsets {
    public int subsets(int[] values, int n) {
        if (values == null || values.length == 0) return 0;
        if (n == 0) return 1;
        // check if values are positives
        if (n < 0) return 0;
        Map<String, Integer> cache = new HashMap<>();
        return subsets(values, 0, n, cache);
    }

    private int subsets(int[] values, int i, int n, Map<String, Integer> cache) {
        if (n == 0) return 1;
        if (i >= values.length) return 0;
        String key = i + ":" + n;
        if (cache.containsKey(key)) return cache.get(key);
        int result = subsets(values, i + 1, n, cache);
        if (values[i] <= n) {
            result += subsets(values, i + 1, n - values[i], cache);
        }
        cache.put(key, result);
        return result;
    }

}
