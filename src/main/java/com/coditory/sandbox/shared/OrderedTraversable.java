package com.coditory.sandbox.shared;

public interface OrderedTraversable extends Traversable {
    void traverseInOrder(Visitor visitor);

    void traversePreOrder(Visitor visitor);

    void traversePostOrder(Visitor visitor);

    @Override
    default void traverseDfs(Visitor visitor) {
        traverseInOrder(visitor);
    }
}