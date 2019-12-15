package com.coditory.sandbox.array;

import java.util.Random;

public class Shuffle {
    private static final Random RANDOM = new Random();

    static void shuffle(int[] values) {
        for (int i = 1; i < values.length; ++i) {
            int idx = RANDOM.nextInt(i + 1);
            swap(values, i, idx);
        }
    }

    private static void swap(int[] values, int i, int j) {
        int tmp = values[j];
        values[j] = values[i];
        values[i] = tmp;
    }
}
