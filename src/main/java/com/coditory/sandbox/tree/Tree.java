package com.coditory.sandbox.tree;

import com.coditory.sandbox.shared.OrderedTraversable;

import java.util.Objects;

public interface Tree extends OrderedTraversable {
    void add(int value);

    Integer min();

    Integer max();

    Integer floor(int value);

    Integer ceil(int value);

    Integer rank(int value);

    boolean contains(int value);

    Median median();

    final class Median {
        static Median singleValueMedian(int value) {
            return new Median(value, value);
        }

        static Median twoValuesMedian(int first, int second) {
            int smaller = Math.min(first, second);
            int bigger = Math.max(first, second);
            return new Median(smaller, bigger);
        }

        private final int firstValue;

        private final int secondValue;

        private Median(int firstValue, int secondValue) {
            super();
            this.firstValue = firstValue;
            this.secondValue = secondValue;
        }

        public boolean isSingleValue() {
            return firstValue == secondValue;
        }

        public int getValue() {
            return firstValue;
        }

        public int getFirstValue() {
            return firstValue;
        }

        public int getSecondValue() {
            return secondValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Median median = (Median) o;
            return firstValue == median.firstValue &&
                    secondValue == median.secondValue;
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstValue, secondValue);
        }

        @Override
        public String toString() {
            if (firstValue == secondValue) {
                return "Median{value=" + firstValue + '}';
            }
            return "Median{" +
                    "firstValue=" + firstValue +
                    ", secondValue=" + secondValue +
                    '}';
        }
    }
}
