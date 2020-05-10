package com.coditory.sandbox.cache;

import java.util.HashMap;
import java.util.Map;

public class LruCache<KEY, VALUE> implements Cache<KEY, VALUE> {
    private class Node {
        private final KEY key;
        private VALUE value;

        private Node next;
        private Node prev;

        public Node(KEY key, VALUE value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<KEY, Node> map = new HashMap<>();
    private Node first;
    private Node last;

    public LruCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Expected capacity > 0");
        }
        this.capacity = capacity;
    }

    @Override
    public VALUE get(KEY key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node node = map.get(key);
        remove(node);
        enquque(node);
        return node.value;
    }

    private Node pop() {
        if (first == null) {
            return null;
        }
        Node node = first;
        remove(node);
        return node;
    }

    private void enquque(Node node) {
        node.next = null;
        node.prev = null;
        if (last != null) {
            node.next = last;
            last.prev = node;
        }
        last = node;
        if (first == null) {
            first = node;
        }
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        if (first == node) {
            first = prev;
        }
        if (last == node) {
            last = next;
        }
        node.prev = null;
        node.next = null;
    }

    @Override
    public void put(KEY key, VALUE value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            enquque(node);
        } else {
            if (map.size() + 1 > capacity) {
                Node node = pop();
                map.remove(node.key);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            enquque(node);
        }
    }
}
