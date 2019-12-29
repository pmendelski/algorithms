package com.coditory.sandbox.shared;

public interface Traversable {
    void traverseDfs(Visitor visitor);

    void traverseBfs(Visitor visitor);

}