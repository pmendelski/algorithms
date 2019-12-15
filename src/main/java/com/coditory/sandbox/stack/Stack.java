package com.coditory.sandbox.stack;

public interface Stack<T> {
    void push(T value);

    T pop();

    T peek();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
