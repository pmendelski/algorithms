package com.coditory.sandbox.dynamic;

public class MaxRectangle {
    public int maxRect(int[] histogram) {
        if (histogram == null || histogram.length == 0) return 0;
        int top = 0;
        int[] positions = new int[histogram.length];
        int[] heights = new int[histogram.length];
        positions[top] = 0;
        heights[top] = histogram[0];
        int result = 0;
        for (int i = 1; i < histogram.length; ++i) {
            while (top > 0 && histogram[i] < heights[top]) {
                int size = heights[top] * (i - positions[top]);
                result = Math.max(result, size);
                top--;
            }
            top++;
            heights[top] = histogram[i];
            positions[top] = i;
        }
        while (top > 0) {
            int size = heights[top] * (histogram.length - positions[top]);
            result = Math.max(result, size);
            top--;
        }
        return result;
    }

}
