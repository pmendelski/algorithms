package com.coditory.sandbox.dynamic;

import static java.lang.System.arraycopy;

public class MaxSquare {
    public int maxSquare(int[][] values) {
        // Bottom up dynamic programming
        // complexity: O(n^2)
        if (values == null || values.length == 0) return 0;
        int rows = values.length;
        int cols = values[0].length;
        int[][] cache = new int[rows][];
        for (int row = 0; row < rows; ++row) {
            cache[row] = new int[cols];
            arraycopy(values[row], 0, cache[row], 0, cols);
        }
        int result = 0;
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (row == 0 || col == 0) {
                    cache[row][col] = 0;
                } else if (values[row][col] > 0) {
                    int neighbourMin = Math.min(cache[row][col - 1], cache[row - 1][col]);
                    neighbourMin = Math.min(neighbourMin, cache[row - 1][col - 1]);
                    cache[row][col] = neighbourMin + 1;
                    result = Math.max(result, cache[row][col]);
                }
            }
        }
        return result;
    }

}
