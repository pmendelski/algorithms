package com.coditory.sandbox.graph;

import com.coditory.sandbox.shared.Traversable;

import java.util.List;
import java.util.Set;

public interface Graph extends Traversable {
    void addNode(int value);

    void addEdge(int from, int to, int weight);

    default void addEdge(int from, int to) {
        addEdge(from, to, 1);
    }

    List<Integer> shortestPath(int from, int to);

    default void addPath(int... values) {
        if (values == null || values.length == 0) {
            return;
        }
        int previous = values[0];
        for (int i = 1; i < values.length; ++i) {
            addEdge(previous, values[i]);
            previous = values[i];
        }
    }

    List<Set<Integer>> colorize();

    default void addUndirectedEdge(int a, int b, int weight) {
        addEdge(a, b, weight);
        addEdge(b, a, weight);
    }

    default void addUndirectedEdge(int a, int b) {
        addUndirectedEdge(a, b, 1);
    }

    default void addUndirectedPath(int... values) {
        if (values == null || values.length == 0) {
            return;
        }
        int previous = values[0];
        for (int i = 1; i < values.length; ++i) {
            addEdge(previous, values[i]);
            addEdge(values[i], previous);
            previous = values[i];
        }
    }
}
