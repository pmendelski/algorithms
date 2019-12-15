package com.coditory.sandbox.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.coditory.sandbox.sort.HeapSort.heapSort;
import static com.coditory.sandbox.sort.InsertionSort.insertionSort;
import static com.coditory.sandbox.sort.MergeSort.mergeSort;
import static com.coditory.sandbox.sort.QuickSort.quickSort;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class SortSpec {
    abstract void sort(int[] values);

    @Test
    public void shouldSortSmallRandomExample() {
        sortSpec(7, 6, 3, 1, 4, 5);
    }

    @Test
    public void shouldSortSmallReverseSortedExample() {
        sortSpec(7, 6, 5, 4, 3, 2, 1);
    }

    @Test
    public void shouldSortSmallSortedExample() {
        sortSpec(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    public void shouldSortRandomExample() {
        sortSpec(1, 3, 5, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
    }

    @Test
    public void shouldSortReverseSortedExample() {
        sortSpec(20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
    }

    @Test
    public void shouldSortSortedExample() {
        sortSpec(14, 1, 2, 8, 3, 9, 15, 16, 4, 12, 17, 19, 20, 13, 5, 11, 6, 18, 10, 7);
    }

    private void sortSpec(int... values) {
        // when
        sort(values);
        // then
        assertTrue(isSorted(values), "Expected sorted result. "
                + "Got: " + Arrays.toString(values) + ". "
                + "First unsorted: " + firstUnsorted(values));
    }

    private boolean isSorted(int[] values) {
        for (int i = 0; i < values.length - 1; ++i) {
            if (values[i] > values[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private int firstUnsorted(int[] values) {
        for (int i = 0; i < values.length - 1; ++i) {
            if (values[i] > values[i + 1]) {
                return values[i];
            }
        }
        return Integer.MIN_VALUE;
    }
}

class QuickSortSpec extends SortSpec {

    @Override
    void sort(int[] values) {
        quickSort(values);
    }
}

class MergeSortSpec extends SortSpec {

    @Override
    void sort(int[] values) {
        mergeSort(values);
    }
}

class HeapSortSpec extends SortSpec {

    @Override
    void sort(int[] values) {
        heapSort(values);
    }
}

class InsertionSortSpec extends SortSpec {

    @Override
    void sort(int[] values) {
        insertionSort(values);
    }
}