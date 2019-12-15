package com.coditory.sandbox.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.coditory.sandbox.array.Shuffle.shuffle;

public class ShuffleSpec {
    @Test
    public void shouldShuffleElements() {
        // given
        int[] input = new int[]{0, 2, 4, 6, 8};
        int[] copy = Arrays.copyOf(input, input.length);

        // when
        shuffle(input);

        // then
        Assertions.assertFalse(Arrays.equals(copy, input));
    }
}
