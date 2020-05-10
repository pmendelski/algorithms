package com.coditory.sandbox.cache;

public interface Cache<KEY, VALUE> {
    VALUE get(KEY key);

    void put(KEY key, VALUE value);
}
