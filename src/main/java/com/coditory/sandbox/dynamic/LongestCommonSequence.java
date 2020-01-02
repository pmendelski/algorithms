package com.coditory.sandbox.dynamic;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSequence {
    /**
     * Returns the length of a longest common sequence.
     * Examples:
     * longestCommonSeq("BATD", "ABACD") -> "BAD" -> 3
     * longestCommonSeq("BATD", "ABCBABD") -> "BAD" -> 3
     * longestCommonSeq("BATD", "AABCD") -> "BD" -> 2
     * longestCommonSeq("BATD", "BATD") -> "BATD" -> 2
     * longestCommonSeq("BATD", "") -> "" -> 3
     * longestCommonSeq("ABBA", "ABAB") -> "ABA" -> 3
     */
    public int longestCommonSeq(String p, String q) {
        if (p == null || p.length() == 0) return 0;
        if (q == null || q.length() == 0) return 0;
        // Time complexity
        // - without cache: O(2^(n+m))
        // - with cache: O(nm)
        // where n, m are string lengths
        Map<String, Integer> cache = new HashMap<>();
        return longestCommonSeq(p, 0, q, 0, cache);
    }

    private int longestCommonSeq(
            String p, int ip,
            String q, int iq,
            Map<String, Integer> cache
    ) {
        if (ip >= p.length()) return 0;
        if (iq >= q.length()) return 0;
        String key = ip + ":" + iq;
        if (cache.containsKey(key)) return cache.get(key);
        int result = 0;
        if (p.charAt(ip) == q.charAt(iq)) {
            result = Math.max(result, 1 + longestCommonSeq(p, ip + 1, q, iq + 1, cache));
        } else {
            result = longestCommonSeq(p, ip + 1, q, iq, cache);
            result = Math.max(result, longestCommonSeq(p, ip, q, iq + 1, cache));
        }
        cache.put(key, result);
        return result;
    }
}
