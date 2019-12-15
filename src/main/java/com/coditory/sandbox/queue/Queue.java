package com.coditory.sandbox.queue;

public interface Queue<T> {
    void offer(T value);

    T poll();

    T peek();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
