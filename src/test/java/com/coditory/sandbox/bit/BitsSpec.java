package com.coditory.sandbox.bit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.coditory.sandbox.shared.Logger.log;
import static java.lang.Integer.toBinaryString;
import static org.junit.jupiter.api.Assertions.*;

public class BitsSpec {
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 100, 200, 1024})
    public void shouldConfirmEvenNumbers(int i) {
        assertTrue(Bits.isEven(i));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 7, 9, 11, 101, 201, 1025})
    public void shouldNotConfirmNotEvenNumbers(int i) {
        assertFalse(Bits.isEven(i));
    }

    @Test
    public void shouldConfirmSetBits() {
        int[] bits = new int[]{0, 2, 3, 4, 6};
        int val = 0b1011101;

        assertTrue(Bits.areBitsSet(val, bits));
    }

    @Test
    public void shouldNotConfirmNotSetBits() {
        int[] bits = new int[]{0, 1, 3, 4, 6};
        int val = 0b1011101;

        assertFalse(Bits.areBitsSet(val, bits));
    }

    @Test
    public void shouldSetNthBit() {
        // given
        // 0b00000000000000000000000000000000
        int value = 0;

        // when
        value = Bits.setBit(value, 4);

        // then
        assertEquals("10000", toBinaryString(value));
    }

    @Test
    public void shouldUnsetNthBit() {
        // given
        // 0b01111111111111111111111111111111
        int value = Integer.MAX_VALUE;

        // when
        value = Bits.unsetBit(value, 4);

        // then
        assertEquals("1111111111111111111111111101111", toBinaryString(value));
    }

    @Test
    public void sandbox() {
        log(">" + toBin(6) + "<");
        log(">" + toBin(6 << 1) + " <> " + (6 << 1) + "<");
        log(">" + toBin(-6 << 1) + " <> " + (-6 << 1) + "<");
        log(">" + toBin(6 >> 1) + " <> " + (6 >> 1) + "<");
        log(">" + toBin(6 >>> 1) + " <> " + (6 >>> 1) + "<");
        log(">" + toBin(-6 >> 1) + " <> " + (-6 >> 1) + "<");
        log(">" + toBin(-6 >>> 1) + " <> " + (-6 >>> 1) + "<");
    }

    private static String toBin(int val) {
        return Bits.toBinaryString(val);
    }
}
