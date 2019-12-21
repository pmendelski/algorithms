package com.coditory.sandbox.bit;

public class Bits {
    public static boolean isEven(int bits) {
        return (bits & 1) == 0;
    }

    public static boolean isOdd(int bits) {
        return (bits & 1) == 1;
    }

    public static boolean isBitSet(int bits, int i) {
        return (bits & (1 << i)) > 0;
    }

    public static boolean areBitsSet(int bits, int[] indexes) {
        int mask = 0;
        for (int i : indexes) {
            mask = mask | (1 << i);
        }
        return (bits & mask) == mask;
    }

    public static int setBit(int bits, int i) {
        return bits | 1 << i;
    }

    public static int unsetBit(int bits, int i) {
        return bits & ~(1 << i);
    }

    public static int multiplyBy2(int val) {
        return val << 1;
    }

    public static int divideBy2(int val) {
        return val >> 1;
    }

    public static boolean isPowerOf2(int val) {
        return (val & val - 1) == 0;
    }

    public static int unsetLessSignificantBit(int val) {
        return val & val - 1;
    }

    public static String toBinaryString(int val) {
        String binStr = Integer.toBinaryString(val);
        return addPaddingOdZeros(binStr, 4);
    }

    private static String addPaddingOdZeros(String binStr, int bytes) {
        int bits = bytes * 8;
        return new StringBuilder(binStr)
                .reverse()
                .append("0".repeat(Math.max(0, bits - binStr.length())))
                .reverse()
                .toString()
                .substring(0, bits - 1);
    }
}
