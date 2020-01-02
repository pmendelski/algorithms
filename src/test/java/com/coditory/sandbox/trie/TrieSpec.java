package com.coditory.sandbox.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrieSpec {
    private final Trie trie = new Trie();

    @BeforeEach
    void setUpTrie() {
        this.trie.addWords(asList("a", "apple", "argument", "aptitude", "ball", "bat"));
    }

    @Test
    void shouldConfirmContainingWord() {
        // when
        boolean result = trie.hasWord("apple");

        // then
        assertTrue(result);
    }

    @Test
    void shouldDiscardContainingWord() {
        // when
        boolean result = trie.hasWord("applle");

        // then
        assertFalse(result);
    }

    @Test
    void shouldConfirmContainingPrefix() {
        // when
        boolean result = trie.hasPrefix("app");

        // then
        assertTrue(result);
    }

    @Test
    void shouldDiscardContainingPrefix() {
        // when
        boolean result = trie.hasPrefix("ax");

        // then
        assertFalse(result);
    }
}
