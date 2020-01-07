package com.coditory.sandbox.map;

import org.junit.jupiter.api.Test;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class MapSpec {
    private final Map<Key, String> map;

    public MapSpec(Map<Key, String> map) {
        this.map = requireNonNull(map);
    }

    @Test
    void shouldAddAndRetrieveAnObject() {
        Key key = key("A", 123);
        map.put(key, "X");
        assertEquals("X", map.get(key));
    }

    @Test
    void shouldOverridePreviousValue() {
        Key key = key("A", 123);
        map.put(key, "X");
        map.put(key, "Y");
        assertEquals("Y", map.get(key));
    }

    @Test
    void shouldAddAndRetrieveAnObjectByKeyHashCodeAndValue() {
        Key a1 = key("A", 1);
        Key b1 = key("B", 1);
        Key b2 = key("B", 2);
        map.put(a1, "X");
        map.put(b1, "Y");
        map.put(b2, "Z");
        assertEquals("X", map.get(a1));
        assertEquals("Y", map.get(b1));
        assertEquals("Z", map.get(b2));
    }

    static Key key(String value, int hashCode) {
        return new Key(value, hashCode);
    }

    private static class Key {
        private final String value;
        private final int hashCode;

        public Key(String value, int hashCode) {
            this.value = value;
            this.hashCode = hashCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return value.equals(key.value);
        }

        @Override
        public int hashCode() {
            return hashCode;
        }
    }
}

class HashMapSpec extends MapSpec {

    public HashMapSpec() {
        super(new HashMap<>());
    }
}
