package com.coditory.sandbox.primes;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.fill;

public class PrimesGenerator {
    List<Integer> primes(int n) {
        // Sieve of Eratosthenes
        // without even numbers to save some memory
        if (n <= 0) return List.of();
        List<Integer> result = new ArrayList<>();
        boolean[] cache = new boolean[n / 2];
        fill(cache, true);
        for (int i = 1; i < n; ++i) {
            if (i <= 2) {
                result.add(i);
            } else if (i % 2 == 1 && cache[i / 2]) {
                result.add(i);
                falsifyMultiplications(cache, i);
            }
        }
        return result;
    }

    private void falsifyMultiplications(boolean[] cache, int prime) {
        for (int i = 3; prime * i / 2 < cache.length; i += 2) {
            cache[prime * i / 2] = false;
        }
    }
}
