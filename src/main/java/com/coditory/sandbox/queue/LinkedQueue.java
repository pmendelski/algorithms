package com.coditory.sandbox.queue;

public class LinkedQueue<T> implements Queue<T> {
    private class Node {
        final T value;
        Node next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public void offer(T value) {
        if (first == null) {
            first = new Node(value);
            last = first;
        } else {
            last.next = new Node(value);
            last = last.next;
        }
        size++;
    }

    @Override
    public T poll() {
        if (first == null) {
            throw new IllegalStateException("Empty queue");
        }
        T result = first.value;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
        }
        size--;
        return result;
    }

    @Override
    public T peek() {
        if (first == null) {
            throw new IllegalStateException("Empty queue");
        }
        return first.value;
    }

    @Override
    public int size() {
        return size;
    }

}
