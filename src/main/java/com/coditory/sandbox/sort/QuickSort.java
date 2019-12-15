package com.coditory.sandbox.sort;

import static com.coditory.sandbox.sort.SortUtils.swap;

public final class QuickSort implements Sort {
    private static final QuickSort INSTANCE = new QuickSort();

    public static void quickSort(int[] values) {
        INSTANCE.sort(values);
    }

    public static Sort quickSort() {
        return INSTANCE;
    }

    private QuickSort() {
        // deliberately empty
    }

    @Override
    public void sort(int[] values) {
        if (values == null || values.length <= 1) return;
        sort(values, 0, values.length - 1);
    }

    private void sort(int[] values, int low, int high) {
        if (low >= high) return;
        int pivot = partition(values, low, high);
        sort(values, low, pivot - 1);
        sort(values, pivot + 1, high);
    }

    private int partition(int[] values, int low, int high) {
        int pivot = pivot(values, low, high);
        int i = low;
        int j = high;
        while (i < j) {
            while (values[i] < pivot) i++;
            while (values[j] > pivot) j--;
            if (i < j) swap(values, i, j);
        }
        return j;
    }

    private int pivot(int[] values, int low, int high) {
        int mid = low + (high - low) / 2;
        return median(values[low], values[mid], values[high]);
    }

    private int median(int a, int b, int c) {
        // check b
        if ((a < b && b < c) || (c < b) && (b < a)) return b;
        // check a
        if ((b < a && a < c) || (c < a) && (a < b)) return a;
        return c;
    }

}
