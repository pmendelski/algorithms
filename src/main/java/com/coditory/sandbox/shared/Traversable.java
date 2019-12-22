package com.coditory.sandbox.shared;

public interface Traversable {
    void traverseInOrder(Visitor visitor);

    void traversePreOrder(Visitor visitor);

    void traversePostOrder(Visitor visitor);

    void traverseBfs(Visitor visitor);

    interface Visitor {
        void visit(int item);
    }
}