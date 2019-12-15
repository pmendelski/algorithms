package com.coditory.sandbox.queue;

public class ArrayQueue<T> implements Queue<T> {
    private final T[] values;
    private int first = -1;
    private int last = -1;
    private int size = 0;

    public ArrayQueue(int size) {
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
        }
    }

    @Override
    public T poll() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        T result = values[first];
        first = index(first + 1);
        size--;
        if (size == 0) {
            first = -1;
            last = -1;
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

    private int index(int value) {
        return value % values.length;
    }

}
