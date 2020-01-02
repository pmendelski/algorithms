package com.coditory.sandbox.dynamic;

public class Fibonacci {
    public int fibonacci(int n) {
        if (n <= 0) return 0;
        if (n < 2) return 1;
        int prev = 1;
        int result = 1;
        for (int i = 2; i < n; ++i) {
            int pprev = prev;
            prev = result;
            result = prev + pprev;
        }
        return result;
    }

}
