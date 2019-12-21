package com.coditory.sandbox.trie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Trie {
    public static class Node {
        private final Map<Integer, Node> children = new HashMap<>();
        private boolean wordEnding = false;

        public boolean isWordEnding() {
            return wordEnding;
        }

        void addWord(int[] codes, int index) {
            int next = index + 1;
            if (next >= codes.length) {
                wordEnding = true;
                return;
            }
            children.putIfAbsent(codes[next], new Node());
            Node child = children.get(codes[next]);
            child.addWord(codes, next);
        }

        Node findNode(int[] codes, int index) {
            int next = index + 1;
            if (next >= codes.length) return this;
            return children.computeIfPresent(
                    codes[next],
                    (__, child) -> child.findNode(codes, next)
            );
        }
    }


    private final Map<Integer, Node> children = new HashMap<>();

    public void addWords(Collection<String> words) {
        if (words == null || words.isEmpty()) return;
        words.forEach(this::addWord);
    }

    public void addWord(String word) {
        if (word == null || word.isEmpty()) return;
        int[] codes = word.codePoints().toArray();
        int first = codes[0];
        children.putIfAbsent(first, new Node());
        Node child = children.get(first);
        child.addWord(codes, 0);
    }

    public boolean hasPrefix(String prefix) {
        return findNode(prefix) != null;
    }

    public boolean hasWord(String word) {
        Node child = findNode(word);
        return child != null && child.isWordEnding();
    }

    private Node findNode(String word) {
        int[] codes = word.codePoints().toArray();
        return children.computeIfPresent(
                codes[0],
                (__, child) -> child.findNode(codes, 0)
        );
    }

}
