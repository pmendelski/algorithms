package com.coditory.sandbox.dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestCommonSequenceTest {
    LongestCommonSequence s = new LongestCommonSequence();

    @Test
    void shouldReturnLongestCommonSequence() {
        assertEquals(3, lcs("BATD", "ABACD"));
        assertEquals(3, lcs("BATD", "ABCBABD"));
        assertEquals(2, lcs("BATD", "AABCD"));
        assertEquals(4, lcs("BATD", "BATD"));
        assertEquals(0, lcs("BATD", ""));
        assertEquals(3, lcs("ABBA", "ABAB"));
    }

    private int lcs(String a, String b) {
        return s.longestCommonSeq(a, b);
    }
}