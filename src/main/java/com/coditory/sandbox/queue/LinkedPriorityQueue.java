package com.coditory.sandbox.queue;

import java.util.Comparator;

public class LinkedPriorityQueue<T> implements Queue<T> {
    private static class Node<T> {
        final T value;
        final Node<T> left;
        final Node<T> right;

        Node(T value) {
            this(value, null, null);
        }

        Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private final Comparator<T> comparator;
    private int size = 0;
    private Node<T> top;

    public LinkedPriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void offer(T value) {
        top = offer(top, value);
        size++;
    }

    private Node<T> offer(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }
        if (smaller(value, node.value)) {
            Node<T> newNode = new Node<>(value, node.left, node.right);
            return offer(newNode, node.value);
        }
        if (node.left == null) {
            Node<T> newLeft = new Node<>(value);
            return new Node<>(node.value, newLeft, node.right);
        }
        if (node.right == null) {
            Node<T> newRight = new Node<>(value);
            return new Node<>(node.value, node.left, newRight);
        }
        if (smaller(node.left, node.right)) {
            return offer(node.right, value);
        }
        return offer(node.left, value);
    }

    @Override
    public T poll() {
        if (top == null) {
            throw new IllegalStateException("Queue is empty");
        }
        T result = top.value;
        top = remove(top);
        size--;
        return result;
    }

    private Node<T> remove(Node<T> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null && node.right == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        if (smaller(node.left, node.right)) {
            Node<T> newLeft = remove(node.left);
            return new Node<>(node.left.value, newLeft, node.right);
        }
        Node<T> newRight = remove(node.right);
        return new Node<>(node.right.value, node.left, newRight);
    }

    @Override
    public T peek() {
        if (top == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return top.value;
    }

    @Override
    public int size() {
        return size;
    }

    private boolean smaller(Node<T> a, Node<T> b) {
        return smaller(a.value, b.value);
    }

    private boolean smaller(T a, T b) {
        return comparator.compare(a, b) < 0;
    }
}
