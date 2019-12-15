package com.coditory.sandbox.sort;

import static com.coditory.sandbox.sort.SortUtils.swap;

public final class HeapSort implements Sort {
    private static final HeapSort INSTANCE = new HeapSort();

    public static void heapSort(int[] values) {
        INSTANCE.sort(values);
    }

    public static Sort heapSort() {
        return INSTANCE;
    }

    private HeapSort() {
        // deliberately empty
    }

    @Override
    public void sort(int[] values) {
        if (values == null || values.length <= 1) return;
        buildHeap(values);
        int n = values.length;
        for (int i = 1; i < n; ++i) {
            swap(values, 0, n - i);
            heapify(values, 0, n - i);
        }
    }

    private void buildHeap(int[] values) {
        int n = values.length;
        for (int i = n / 2; i >= 0; --i) {
            heapify(values, i, n);
        }
    }

    private void heapify(int[] values, int root, int n) {
        while (true) {
            int max = root;
            int left = root * 2 + 1;
            int right = root * 2 + 2;
            if (left < n && values[left] > values[max]) {
                max = left;
            }
            if (right < n && values[right] > values[max]) {
                max = right;
            }
            if (root != max) {
                swap(values, root, max);
                root = max;
            } else {
                break;
            }
        }
    }
}
