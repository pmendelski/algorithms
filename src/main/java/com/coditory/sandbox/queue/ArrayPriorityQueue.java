package com.coditory.sandbox.queue;

import java.util.Comparator;

public class ArrayPriorityQueue<T> implements Queue<T> {
    private final Comparator<T> comparator;
    private final T[] values;
    private int first = -1;
    private int last = -1;
    private int size = 0;

    public ArrayPriorityQueue(Comparator<T> comparator, int size) {
        this.comparator = comparator;
        this.values = (T[]) new Object[size];
    }

    @Override
    public void offer(T value) {
        if (size == values.length) {
            throw new IllegalStateException("Queue is full");
        }
        last = index(last + 1);
        values[last] = value;
        size++;
        if (size == 1) {
            first = last;
        } else {
            heapifyUp();
        }
    }

    @Override
    public T poll() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        T result = values[first];
        values[first] = values[last];
        values[last] = null;
        last = index(last - 1);
        size--;
        if (size == 0) {
            first = -1;
            last = -1;
        } else {
            heapifyDown();
        }
        return result;
    }

    @Override
    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        return values[first];
    }

    @Override
    public int size() {
        return size;
    }

    private void heapifyDown() {
        int root = 0;
        int max = -1;
        while (true) {
            max = root;
            int left = root * 2 + 1;
            int right = root * 2 + 2;
            int rootIdx = index(root + first);
            int leftIdx = index(left + first);
            int rightIdx = index(right + first);
            if (left < size && smaller(leftIdx, rootIdx)) {
                max = left;
            }
            if (right < size && smaller(rightIdx, rootIdx)) {
                max = right;
            }
            if (max != root) {
                swap(index(max + first), rootIdx);
                root = max;
            } else {
                break;
            }
        }
    }

    private void heapifyUp() {
        int child = last;
        int parent = parent(child);
        do {
            if (smaller(child, parent)) {
                swap(child, parent);
                child = parent;
                parent = parent(parent);
            } else {
                parent = child;
            }
        } while (parent != child && parent != first);
    }

    private boolean smaller(int first, int second) {
        T firstValue = values[first];
        T secondValue = values[second];
        return comparator.compare(firstValue, secondValue) < 0;
    }

    private int parent(int index) {
        return index((index - 1) / 2);
    }

    private void swap(int a, int b) {
        T tmp = values[a];
        values[a] = values[b];
        values[b] = tmp;
    }

    private int index(int value) {
        return value % values.length;
    }

}
