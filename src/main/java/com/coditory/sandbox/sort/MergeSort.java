package com.coditory.sandbox.sort;

public final class MergeSort implements Sort {
    private static final MergeSort INSTANCE = new MergeSort();

    public static void mergeSort(int[] values) {
        INSTANCE.sort(values);
    }

    public static Sort mergeSort() {
        return INSTANCE;
    }

    private MergeSort() {
        // deliberately empty
    }

    @Override
    public void sort(int[] values) {
        if (values == null || values.length <= 1) return;
        int[] aux = new int[values.length];
        sort(values, aux, 0, values.length - 1);
    }

    private void sort(int[] values, int[] aux, int low, int high) {
        if (low >= high) return;
        int mid = low + (high - low) / 2;
        mid = mid == low ? high : mid;
        sort(values, aux, low, mid - 1);
        sort(values, aux, mid, high);
        merge(values, aux, low, high, mid);
    }

    private void merge(int[] values, int[] aux, int low, int high, int mid) {
        int i = low;
        int j = mid;
        for (int k = low; k <= high; ++k) {
            if (aux[i] <= aux[j]) {
                values[k] = aux[i];
                i++;
            } else {
                values[k] = aux[j];
                j++;
            }
        }
    }
}
