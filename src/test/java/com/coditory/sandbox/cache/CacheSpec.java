package com.coditory.sandbox.cache;

import org.junit.jupiter.api.Test;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

abstract class CacheSpec {
    protected final Cache<String, String> cache;

    public CacheSpec(Cache<String, String> cache) {
        this.cache = requireNonNull(cache);
    }

    @Test
    void shouldAddAndRetrieveAnObject() {
        cache.put("a", "A");
        cache.put("b", "B");
        cache.put("c", "C");
        assertEquals("A", cache.get("a"));
        assertEquals("B", cache.get("b"));
        assertEquals("C", cache.get("c"));
    }
}

class LruCacheSpec extends CacheSpec {
    public LruCacheSpec() {
        super(new LruCache<>(3));
    }

    @Test
    void shouldDropFirstAddedItem() {
        cache.put("a", "A");
        cache.put("b", "B");
        cache.put("c", "C");
        cache.put("d", "D");
        assertNull(cache.get("a"));
        assertEquals("B", cache.get("b"));
        assertEquals("C", cache.get("c"));
        assertEquals("D", cache.get("d"));
    }

    @Test
    void shouldDropLruItemByRead() {
        cache.put("a", "A");
        cache.put("b", "B");
        cache.put("c", "C");
        assertEquals("A", cache.get("a"));
        cache.put("d", "D");
        assertEquals("A", cache.get("a"));
        assertNull(cache.get("b"));
        assertEquals("C", cache.get("c"));
        assertEquals("D", cache.get("d"));
    }

    @Test
    void shouldDropLruItemByWrite() {
        cache.put("a", "A");
        cache.put("b", "B");
        cache.put("c", "C");
        cache.put("a", "A");
        cache.put("d", "D");
        assertEquals("A", cache.get("a"));
        assertNull(cache.get("b"));
        assertEquals("C", cache.get("c"));
        assertEquals("D", cache.get("d"));
    }
}