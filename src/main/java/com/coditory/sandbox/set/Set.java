package com.coditory.sandbox.set;

public interface Set<T> {
    boolean contains(T value);

    void add(T value);

    void remove(T value);

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
