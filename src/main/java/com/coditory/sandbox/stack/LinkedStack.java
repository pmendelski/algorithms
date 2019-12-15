package com.coditory.sandbox.stack;

public class LinkedStack<T> implements Stack<T> {
    private static class Node<T> {
        final T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> top;
    private int size = 0;

    @Override
    public void push(T value) {
        top = new Node<>(value, top);
        size++;
    }

    @Override
    public T pop() {
        if (top == null) {
            throw new IllegalStateException("Empty stack");
        }
        T result = top.value;
        top = top.next;
        size--;
        return result;
    }

    @Override
    public T peek() {
        if (top == null) {
            throw new IllegalStateException("Empty stack");
        }
        return top.value;
    }

    @Override
    public int size() {
        return size;
    }
}
