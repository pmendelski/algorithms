package com.coditory.sandbox.primes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PrimesGeneratorSpec {

    private final PrimesGenerator primesGenerator = new PrimesGenerator();

    @Test
    void shouldGeneratePrimeNumbers() {
        List<Integer> result = primesGenerator.primes(120);
        Assertions.assertIterableEquals(
                List.of(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113),
                result);
    }

    @Test
    void shouldGeneratePrimeNumbersFromEvenNumbers() {
        List<Integer> result = primesGenerator.primes(14);
        Assertions.assertIterableEquals(List.of(1, 2, 3, 5, 7, 11, 13), result);
    }

    @Test
    void shouldGeneratePrimeNumbersFromOddNumbers() {
        List<Integer> result = primesGenerator.primes(11);
        Assertions.assertIterableEquals(List.of(1, 2, 3, 5, 7), result);
    }


}