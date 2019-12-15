package com.coditory.sandbox.shared;

import java.util.List;
import java.util.function.BiFunction;

public final class Preconditions {
    private Preconditions() {
        throw new IllegalStateException("Do not instantiate utility class");
    }

    @SafeVarargs
    static <T> T expectAll(T value, String message, BiFunction<T, String, T>... expects) {
        for (BiFunction<T, String, T> expect : expects) {
            expect.apply(value, message);
        }
        return value;
    }

    public static <T> T expectNonNull(T value) {
        return expectNonNull(value, "Expected non null value");
    }

    public static <T> T expectNonNull(T value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static String expectNonEmpty(String value) {
        return expectNonEmpty(value, "Expected non empty string. Got: " + value);
    }

    public static String expectNonEmpty(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static <E> List<E> expectNonEmpty(List<E> list) {
        return expectNonEmpty(list, "Expected non empty list. Got: " + list);
    }

    public static <E> List<E> expectNonEmpty(List<E> list, String message) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return list;
    }
}