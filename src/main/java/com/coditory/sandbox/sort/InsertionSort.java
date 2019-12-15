package com.coditory.sandbox.sort;

import static com.coditory.sandbox.sort.SortUtils.swap;

public final class InsertionSort implements Sort {
    private static final InsertionSort INSTANCE = new InsertionSort();

    public static void insertionSort(int[] values) {
        INSTANCE.sort(values);
    }

    public static Sort insertionSort() {
        return INSTANCE;
    }

    private InsertionSort() {
        // deliberately empty
    }

    @Override
    public void sort(int[] values) {
        if (values == null || values.length <= 1) return;
        for (int i = 1; i < values.length; ++i) {
            int value = values[i];
            int j = i - 1;
            while (j >= 0 && values[j] > value) {
                swap(values, j, j + 1);
                j--;
            }
        }
    }
}
