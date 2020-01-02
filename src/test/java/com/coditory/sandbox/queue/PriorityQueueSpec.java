package com.coditory.sandbox.queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public abstract class PriorityQueueSpec {
    private final Queue<Integer> queue;

    public PriorityQueueSpec(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Test
    void shouldEnqueueElements() {
        // when
        queue.offer(1);
        queue.offer(3);
        queue.offer(2);

        // then
        assertEquals(3, queue.size());
        assertIterableEquals(asList(3, 2, 1), queueToList(queue));
    }

    @Test
    void shouldDequeueOneElement() {
        // given
        queue.offer(1);
        queue.offer(3);
        queue.offer(2);

        // when
        int dequeued = queue.poll();

        // then
        assertEquals(3, dequeued);
        assertEquals(2, queue.size());
        assertIterableEquals(asList(2, 1), queueToList(queue));
    }

    private <T> List<T> queueToList(Queue<T> queue) {
        List<T> result = new ArrayList<>(queue.size());
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }
}

class ArrayPriorityQueueSpec extends PriorityQueueSpec {
    public ArrayPriorityQueueSpec() {
        super(new ArrayPriorityQueue<>(Comparator.<Integer>reverseOrder(), 100));
    }
}

class LinkedPriorityQueueSpec extends PriorityQueueSpec {
    public LinkedPriorityQueueSpec() {
        super(new LinkedPriorityQueue<>(Comparator.<Integer>reverseOrder()));
    }
}