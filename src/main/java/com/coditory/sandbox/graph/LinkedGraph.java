package com.coditory.sandbox.graph;

import com.coditory.sandbox.shared.Visitor;

import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class LinkedGraph implements Graph {
    private static class Node {
        private final int value;
        private final List<Node> parents = new ArrayList<>();
        private final List<Node> children = new ArrayList<>();
        private final List<Node> adjacent = new ArrayList<>();
        private final Map<Node, Integer> childrenWeights = new HashMap<>();

        Node(int value) {
            this.value = value;
        }

        boolean hasParent() {
            return !parents.isEmpty();
        }

        int degree() {
            return adjacent.size();
        }

        int weight(Node child) {
            if (!childrenWeights.containsKey(child)) {
                throw new IllegalArgumentException("Could not locate child");
            }
            return childrenWeights.get(child);
        }

        void add(Node node, int weight) {
            this.children.add(node);
            this.childrenWeights.put(node, weight);
            this.adjacent.add(node);
            node.parents.add(this);
            node.adjacent.add(this);
        }

        void traverseDfs(Visitor visitor, Set<Node> visited) {
            if (visited.contains(this)) {
                return;
            }
            visitor.visit(this.value);
            visited.add(this);
            for (Node node : children) {
                node.traverseDfs(visitor, visited);
            }
        }
    }

    Map<Integer, Node> nodesByValue = new HashMap<>();
    List<Node> nodes = new ArrayList<>();

    @Override
    public void addNode(int value) {
        if (nodesByValue.containsKey(value)) {
            return;
        }
        Node node = new Node(value);
        nodesByValue.put(value, node);
        nodes.add(node);
    }

    @Override
    public void addEdge(int from, int to, int weight) {
        addNode(from);
        addNode(to);
        Node fromNode = nodesByValue.get(from);
        Node toNode = nodesByValue.get(to);
        fromNode.add(toNode, weight);
    }

    @Override
    public List<Integer> shortestPath(int from, int to) {
        // Dijkstra O(N^2)
        Node fromNode = nodesByValue.get(from);
        Node toNode = nodesByValue.get(to);
        if (fromNode == null || toNode == null) {
            return List.of();
        }
        if (toNode == fromNode) {
            return List.of(fromNode.value);
        }
        // initialize distances
        Map<Node, Integer> distances = new HashMap<>();
        for (Node node : nodes) {
            distances.put(node, MAX_VALUE);
        }
        distances.put(fromNode, 0);
        // initialize path
        Map<Node, Node> path = new HashMap<>();
        path.put(fromNode, fromNode);
        // compute
        Set<Node> unvisited = new HashSet<>(nodes);
        while (!unvisited.isEmpty()) {
            Node node = findNodeWithMinDistance(unvisited, distances);
            int nodeDistance = distances.get(node);
            unvisited.remove(node);
            for (Node adj : node.children) {
                int prevDistance = distances.get(adj);
                int newDistance = nodeDistance + node.weight(adj);
                if (newDistance > 0 && newDistance < prevDistance) {
                    path.put(adj, node);
                    distances.put(adj, newDistance);
                }
            }
        }
        if (distances.get(toNode) == MAX_VALUE) {
            return List.of();
        }
        // build result
        return buildPath(fromNode, toNode, path).stream()
                .map(n -> n.value)
                .collect(toUnmodifiableList());
    }

    private List<Node> buildPath(Node from, Node to, Map<Node, Node> parents) {
        List<Node> path = new ArrayList<>();
        Node last = to;
        path.add(to);
        while (last != from) {
            Node parent = parents.get(last);
            path.add(parent);
            last = parent;
        }
        Collections.reverse(path);
        return path;
    }

    private Node findNodeWithMinDistance(Collection<Node> nodes, Map<Node, Integer> distances) {
        return nodes.stream()
                .min(comparingInt(distances::get))
                .orElse(null);
    }

    @Override
    public void traverseDfs(Visitor visitor) {
        Set<Node> visited = new HashSet<>();
        List<Node> rootNodes = findOrphanNodesOrAny();
        for (Node root : rootNodes) {
            root.traverseDfs(visitor, visited);
        }
    }

    @Override
    public void traverseBfs(Visitor visitor) {
        if (nodes.isEmpty()) {
            return;
        }
        Set<Node> visited = new HashSet<>();
        List<Node> rootNodes = findOrphanNodesOrAny();
        for (Node node : rootNodes) {
            traverseBfs(visitor, node, visited);
        }
    }

    public void traverseBfs(Visitor visitor, Node first, Set<Node> visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(first);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!visited.contains(node)) {
                visited.add(node);
                visitor.visit(node.value);
                queue.addAll(node.children);
            }
        }
    }

    private List<Node> findOrphanNodesOrAny() {
        List<Node> rootNodes = nodes.stream()
                .filter(n -> !n.hasParent())
                .collect(toList());
        if (rootNodes.isEmpty()) {
            rootNodes.add(nodes.get(0));
        }
        return rootNodes;
    }

    @Override
    public List<Set<Integer>> colorize() {
        // greedy algorithm: O(N^2)
        Queue<Node> uncolored = new LinkedList<>(this.nodes);
        // Welsh Powell algorithm: O(N^2) ...but without sorting
        // Queue<Node> uncolored = new PriorityQueue<>(comparingInt(Node::degree));
        // uncolored.addAll(this.nodes);
        List<Set<Node>> colors = new ArrayList<>();
        while (!uncolored.isEmpty()) {
            Node node = uncolored.poll();
            Set<Node> color = colorizeNode(node, uncolored);
            uncolored.removeAll(color);
            colors.add(color);
        }
        return colors.stream()
                .map(color -> color.stream().map(n -> n.value).collect(toSet()))
                .collect(toList());
    }

    public Set<Node> colorizeNode(Node node, Collection<Node> uncoloredNodes) {
        Set<Node> adjacent = new HashSet<>(node.adjacent);
        Set<Node> color = new HashSet<>(Set.of(node));
        for (Node uncolored : uncoloredNodes) {
            if (!adjacent.contains(uncolored) && !containsAny(color, uncolored.adjacent)) {
                color.add(uncolored);
                break;
            }
        }
        return color;
    }

    private boolean containsAny(Collection<Node> a, Collection<Node> b) {
        return a.stream()
                .anyMatch(b::contains);
    }
}
