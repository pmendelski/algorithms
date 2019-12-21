package com.coditory.sandbox.set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class SetSpec {
    private final Set<Integer> set;

    public SetSpec(Set<Integer> set) {
        this.set = set;
    }

    @Test
    public void shouldAddUniqueItems() {
        // given
        Integer[] a = new Integer[]{1, 2, 2, 3, 3, 3};

        // when
        for (int i : a) {
            set.add(i);
        }

        // then
        assertFalse(set.isEmpty());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
        assertEquals(3, set.size());
    }
}

class BinaryTreeSetSpec extends SetSpec {

    public BinaryTreeSetSpec() {
        super(new BinaryTreeSet<>());
    }
}