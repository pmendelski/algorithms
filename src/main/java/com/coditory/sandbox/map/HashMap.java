package com.coditory.sandbox.map;

public class HashMap<K, V> implements Map<K, V> {
    private final Entry<K, V>[] buckets;

    public HashMap() {
        this(256);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int buckets) {
        this.buckets = (Entry<K, V>[]) new Entry[buckets];
    }

    @Override
    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        return entry != null
                ? entry.value
                : null;
    }

    @Override
    public void put(K key, V value) {
        Entry entry = getEntry(key);
        if (entry != null) {
            entry.value = value;
        } else {
            int hashcode = key.hashCode();
            int bucket = hashcode % buckets.length;
            buckets[bucket] = new Entry(key, value, buckets[bucket]);
        }
    }

    private Entry<K, V> getEntry(K key) {
        int hashcode = key.hashCode();
        int bucket = hashcode % buckets.length;
        Entry<K, V> entry = buckets[bucket];
        while (entry != null && entry.key != key) {
            entry = entry.next;
        }
        return entry;
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
