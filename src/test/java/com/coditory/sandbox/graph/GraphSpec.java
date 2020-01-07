package com.coditory.sandbox.graph;

import com.coditory.sandbox.base.VisitorToListCollector;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public abstract class GraphSpec {
    private final Graph graph;
    private final VisitorToListCollector visitor = new VisitorToListCollector();

    public GraphSpec(Graph graph) {
        this.graph = requireNonNull(graph);
    }

    @Test
    void shouldDfsNotConnectedNodes() {
        addToGraph(1, 2, 3);
        graph.traverseDfs(visitor);
        assertIterableEquals(asList(1, 2, 3), visitor.getItems());
    }

    @Test
    void shouldBfsNotConnectedNodes() {
        addToGraph(1, 2, 3);
        graph.traverseBfs(visitor);
        assertIterableEquals(asList(1, 2, 3), visitor.getItems());
    }

    @Test
    void shouldDfsCyclicGraph() {
        graph.addPath(1, 2, 3, 1);
        graph.traverseDfs(visitor);
        assertIterableEquals(asList(1, 2, 3), visitor.getItems());
    }

    @Test
    void shouldBfsCyclicGraph() {
        graph.addPath(1, 2, 3, 1);
        graph.traverseBfs(visitor);
        assertIterableEquals(asList(1, 2, 3), visitor.getItems());
    }

    @Test
    void shouldDfsAcyclicGraph() {
        graph.addPath(1, 2, 4);
        graph.addPath(1, 2, 5);
        graph.addPath(1, 3, 6, 2);
        graph.addPath(1, 3, 6, 7);
        graph.traverseDfs(visitor);
        assertIterableEquals(asList(1, 2, 4, 5, 3, 6, 7), visitor.getItems());
    }

    @Test
    void shouldBfsAcyclicGraph() {
        graph.addPath(1, 2, 4);
        graph.addPath(1, 2, 5);
        graph.addPath(1, 3, 6, 2);
        graph.addPath(1, 3, 6, 7);
        graph.traverseBfs(visitor);
        assertIterableEquals(asList(1, 2, 3, 4, 5, 6, 7), visitor.getItems());
    }

    @Test
    void shouldColorizeGraph() {
        // tODO: Double check
        graph.addPath(0, 1, 3, 4);
        graph.addPath(0, 2, 3);
        graph.addPath(1, 2);
        List<Set<Integer>> colors = graph.colorize();
        assertEquals(3, colors.size());
        assertIterableEquals(hashSet(0, 3), colors.get(0));
        assertIterableEquals(hashSet(1, 4), colors.get(1));
        assertIterableEquals(hashSet(2), colors.get(2));
    }

    @Test
    void shouldColorizeEnvelopGraph() {
        graph.addPath(0, 1, 2, 3, 0);
        graph.addPath(0, 4, 1);
        List<Set<Integer>> colors = graph.colorize();
        assertEquals(3, colors.size());
        assertIterableEquals(hashSet(0, 2), colors.get(0));
        assertIterableEquals(hashSet(1, 3), colors.get(1));
        assertIterableEquals(hashSet(4), colors.get(2));
    }

    @Test
    void shouldFindShortestPath() {
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 4, 15);
        graph.addEdge(1, 2, 15);
        graph.addEdge(1, 3, 12);
        graph.addEdge(2, 5, 5);
        graph.addEdge(3, 2, 1);
        graph.addEdge(3, 5, 2);
        graph.addEdge(4, 5, 10);
        List<Integer> path = graph.shortestPath(0, 5);
        assertIterableEquals(asList(0, 1, 3, 5), path);
    }

    @Test
    void shouldReturnEmptyPathForNotConnectedNodes() {
        graph.addEdge(0, 1);
        graph.addEdge(3, 2);
        List<Integer> path = graph.shortestPath(0, 2);
        assertIterableEquals(List.of(), path);
    }

    Set<Integer> hashSet(Integer... values) {
        return new HashSet<>(Arrays.asList(values));
    }

    void addToGraph(int... values) {
        Arrays.stream(values)
                .forEach(graph::addNode);
    }
}


class LinkedGraphSpec extends GraphSpec {

    public LinkedGraphSpec() {
        super(new LinkedGraph());
    }
}