package com.coditory.sandbox.shared;

import java.util.Arrays;

public final class Logger {
    private Logger() {

    }

    public static void log(String format, Object... args) {
        for (int i = 0; i < args.length; ++i) {
            if (args[i] instanceof short[]) {
                args[i] = Arrays.toString((short[]) args[i]);
            } else if (args[i] instanceof byte[]) {
                args[i] = Arrays.toString((byte[]) args[i]);
            } else if (args[i] instanceof int[]) {
                args[i] = Arrays.toString((int[]) args[i]);
            } else if (args[i] instanceof long[]) {
                args[i] = Arrays.toString((long[]) args[i]);
            } else if (args[i] instanceof float[]) {
                args[i] = Arrays.toString((float[]) args[i]);
            } else if (args[i] instanceof double[]) {
                args[i] = Arrays.toString((double[]) args[i]);
            } else if (args[i] instanceof char[]) {
                args[i] = Arrays.toString((char[]) args[i]);
            } else if (args[i] instanceof boolean[]) {
                args[i] = Arrays.toString((boolean[]) args[i]);
            } else if (args[i] instanceof Object[]) {
                args[i] = Arrays.toString((Object[]) args[i]);
            }
        }
        String message = String.format(format, args);
        System.out.println(message);
    }
}
