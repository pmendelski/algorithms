package com.coditory.sandbox.base;

import com.coditory.sandbox.shared.Traversable;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class VisitorToListCollector implements Traversable.Visitor {
    private final List<Integer> items = new ArrayList<>();

    @Override
    public void visit(int item) {
        items.add(item);
    }

    public List<Integer> getItems() {
        return unmodifiableList(items);
    }
}
