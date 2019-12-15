package com.coditory.sandbox.sort;

final class SortUtils {
    private SortUtils() {
        throw new IllegalStateException("Do not instantiate utility class");
    }

    static void swap(int[] values, int i, int j) {
        int tmp = values[j];
        values[j] = values[i];
        values[i] = tmp;
    }

    static String toString(int[] values, int low, int high) {
        StringBuilder builder = new StringBuilder("[");
        for (int i = low; i <= high; ++i) {
            if (i > low) {
                builder.append(", ");
            }
            builder.append(values[i]);
        }
        return builder.append("]").toString();
    }
}
