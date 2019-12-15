package com.coditory.sandbox.array;

public final class BinarySearch {
    private BinarySearch() {
        throw new IllegalStateException("Do not instantiate utility class");
    }

    public static int findExact(int[] values, int value) {
        if (values == null || values.length == 0) {
            return -1;
        }
        return findExact(values, 0, values.length - 1, value);
    }

    private static int findExact(int[] values, int low, int high, int value) {
        int mid = low + (high - low) / 2;
        int result = -1;
        if (value > values[mid] && mid < high) {
            result = findExact(values, mid + 1, high, value);
        } else if (value < values[mid] && mid > low) {
            result = findExact(values, low, mid - 1, value);
        } else if (value == values[mid]) {
            result = mid;
        }
        return result;
    }

    public static int findExactOrSmaller(int[] values, int value) {
        if (values == null || values.length == 0) {
            return -1;
        }
        return findExactOrSmaller(values, 0, values.length - 1, value);
    }

    private static int findExactOrSmaller(int[] values, int low, int high, int value) {
        int mid = low + (high - low) / 2;
        int result = -1;
        if (value > values[mid]) {
            if (mid < high) {
                result = findExactOrSmaller(values, mid + 1, high, value);
            } else {
                result = mid;
            }
        } else if (value < values[mid]) {
            if (mid > low) {
                result = findExactOrSmaller(values, low, mid - 1, value);
            } else if (low > 0) {
                result = low - 1;
            }
        } else if (value == values[mid]) {
            result = mid;
        }
        return result;
    }

    public static int findExactOrBigger(int[] values, int value) {
        if (values == null || values.length == 0) {
            return -1;
        }
        return findExactOrBigger(values, 0, values.length - 1, value);
    }

    private static int findExactOrBigger(int[] values, int low, int high, int value) {
        int mid = low + (high - low) / 2;
        int result = -1;
        if (value > values[mid]) {
            if (mid < high) {
                result = findExactOrBigger(values, mid + 1, high, value);
            } else if (high < values.length - 2) {
                result = high + 1;
            }
        } else if (value < values[mid]) {
            if (mid > low) {
                result = findExactOrBigger(values, low, mid - 1, value);
            } else {
                result = mid;
            }
        } else if (value == values[mid]) {
            result = mid;
        }
        return result;
    }
}
