package com.coditory.sandbox.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public abstract class StackSpec {
    private final Stack<Integer> stack;

    public StackSpec(Stack<Integer> stack) {
        this.stack = stack;
    }

    @Test
    public void shouldPushElements() {
        // when
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // then
        assertEquals(3, stack.size());
        assertIterableEquals(asList(3, 2, 1), stackToList(stack));
    }

    @Test
    public void shouldPopOneElement() {
        // given
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // when
        int popped = stack.pop();

        // then
        assertEquals(3, popped);
        assertEquals(2, stack.size());
        assertIterableEquals(asList(2, 1), stackToList(stack));
    }

    @Test
    public void shouldThrowErrorWhenPopOnEmptyStack() {
        assertThrows(IllegalStateException.class, stack::pop);
    }

    @Test
    public void shouldPeekTopElement() {
        // given
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // when
        int peeked = stack.peek();

        // then
        assertEquals(3, peeked);
        assertEquals(3, stack.size());
        assertIterableEquals(asList(3, 2, 1), stackToList(stack));
    }

    private <T> List<T> stackToList(Stack<T> stack) {
        List<T> result = new ArrayList<>(stack.size());
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}

class LinkedStackSpec extends StackSpec {
    public LinkedStackSpec() {
        super(new LinkedStack<>());
    }
}

class ArrayStackSpec extends StackSpec {
    public ArrayStackSpec() {
        super(new ArrayStack<>(100));
    }
}