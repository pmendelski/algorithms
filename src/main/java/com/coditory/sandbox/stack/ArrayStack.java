package com.coditory.sandbox.stack;

public class ArrayStack<T> implements Stack<T> {
    private final T[] values;
    private int top = -1;

    public ArrayStack(int size) {
        this.values = (T[]) new Object[size];
    }

    @Override
    public void push(T value) {
        if (top >= values.length - 1) {
            throw new IllegalStateException("Stack size exceeded");
        }
        values[++top] = value;
    }

    @Override
    public T pop() {
        if (top < 0) {
            throw new IllegalStateException("Empty stack");
        }
        T value = values[top];
        values[top] = null;
        top--;
        return value;
    }

    @Override
    public T peek() {
        if (top < 0) {
            throw new IllegalStateException("Empty stack");
        }
        return values[top];
    }

    @Override
    public int size() {
        return top + 1;
    }
}
