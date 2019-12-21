package com.coditory.sandbox.set;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeSet<T> implements Set<T> {
    private class Node {
        final int hashCode;
        final List<T> values = new ArrayList<>();
        Node left;
        Node right;

        Node(T value) {
            this.hashCode = value.hashCode();
            this.values.add(value);
        }

        boolean add(T value) {
            int hashcode = value.hashCode();
            if (this.hashCode == hashcode) {
                if (values.contains(value)) {
                    return false;
                } else {
                    values.add(value);
                    return true;
                }
            }
            if (hashcode < this.hashCode) {
                if (left != null) {
                    return left.add(value);
                } else {
                    left = new Node(value);
                    return true;
                }
            }
            if (right != null) {
                return right.add(value);
            } else {
                right = new Node(value);
                return true;
            }
        }

        boolean remove(T value) {
            int hashcode = value.hashCode();
            if (this.hashCode == hashcode) {
                return values.remove(value);
            }
            if (left != null && hashcode < this.hashCode) {
                return left.remove(value);
            } else if (right != null && hashcode > this.hashCode) {
                return right.remove(value);
            }
            return false;
        }

        Node find(int hashcode) {
            if (hashcode == this.hashCode) {
                return this;
            }
            Node result = hashcode < this.hashCode
                    ? left : right;
            return result != null
                    ? result.find(hashcode) : null;
        }

        boolean contains(T value) {
            return values.contains(value);
        }
    }

    private Node top;
    private int size = 0;

    @Override
    public boolean contains(T value) {
        if (top == null) return false;
        Node node = top.find(value.hashCode());
        return node.contains(value);
    }

    @Override
    public void add(T value) {
        if (top == null) {
            top = new Node(value);
            size++;
        } else {
            boolean added = top.add(value);
            if (added) {
                size++;
            }
        }
    }

    @Override
    public void remove(T value) {
        if (top != null) {
            boolean removed = top.remove(value);
            if (removed) {
                size--;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }
}
