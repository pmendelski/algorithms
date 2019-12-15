package com.coditory.sandbox.queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public abstract class QueueSpec {
    private final Queue<Integer> queue;

    public QueueSpec(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Test
    public void shouldEnqueueElements() {
        // when
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        // then
        assertEquals(3, queue.size());
        assertIterableEquals(asList(1, 2, 3), queueToList(queue));
    }

    @Test
    public void shouldDequeueOneElement() {
        // given
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        // when
        int dequeued = queue.poll();

        // then
        assertEquals(1, dequeued);
        assertEquals(2, queue.size());
        assertIterableEquals(asList(2, 3), queueToList(queue));
    }

    private <T> List<T> queueToList(Queue<T> queue) {
        List<T> result = new ArrayList<>(queue.size());
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }
}

class LinkedQueueSpec extends QueueSpec {
    public LinkedQueueSpec() {
        super(new LinkedQueue<>());
    }
}

class ArrayQueueSpec extends QueueSpec {
    public ArrayQueueSpec() {
        super(new ArrayQueue<>(100));
    }
}
