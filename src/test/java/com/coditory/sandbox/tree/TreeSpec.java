package com.coditory.sandbox.tree;

import com.coditory.sandbox.base.VisitorToListCollector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static com.coditory.sandbox.tree.Tree.Median.singleValueMedian;
import static com.coditory.sandbox.tree.Tree.Median.twoValuesMedian;
import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

public abstract class TreeSpec {
    private final Tree tree;

    public TreeSpec(Tree tree) {
        this.tree = requireNonNull(tree);
    }

    @Test
    void shouldReturnMinValue() {
        addToTree(8, 3, 0, 1, 3234, 3, 8, 3, 12, -567, 123);
        assertEquals(-567, tree.min());
    }

    @Test
    void shouldReturnMaxValue() {
        addToTree(8, 3, 0, 1, 3234, 3, 8, 3, 12, -567, 123);
        assertEquals(3234, tree.max());
    }

    @Test
    void shouldReturnRankOfValues() {
        // sorted: -567, 0, 1, 3, 8, 12, 123, 3234
        addToTree(8, 3, 0, 1, 3234, 3, 8, 3, 12, -567, 123);
        assertEquals(0, tree.rank(-567));
        assertEquals(1, tree.rank(0));
        assertEquals(2, tree.rank(1));
        assertEquals(3, tree.rank(3));
        assertEquals(4, tree.rank(8));
        assertEquals(5, tree.rank(12));
        assertEquals(6, tree.rank(123));
        assertEquals(7, tree.rank(3234));
    }

    @Test
    void shouldReturnNullWhenRankedValueDoesNotExist() {
        // sorted: -567, 0, 1, 3, 8, 12, 123, 3234
        addToTree(8, 3, 0, 1, 3234, 3, 8, 3, 12, -567, 123);
        assertNull(tree.rank(124));
    }

    @Test
    void shouldReturnMedianWithTwoValuesValue() {
        addToTree(8, 3, 0, 1, 3234, 3, 8, 3, 12, -567, 123);
        assertEquals(twoValuesMedian(3, 8), tree.median());
    }

    @Test
    void shouldReturnMedianWithSingleValue() {
        addToTree(8, 3, 0, 1, 3234, 3, 8, 3, 12, 123);
        assertEquals(singleValueMedian(8), tree.median());
    }

//    @Test
//    void shouldReturnSortedUniqueValues() {
//        addToTree(8, 3, 0, 1, 3234, 3, 8, 3, 12, -567, 123);
//        assertEquals(new Integer[]{-567, 0, 1, 3, 8, 12, 123, 3234}, tree.getSortedArray());
//    }

    @ParameterizedTest
    @CsvSource({
            "-567, -567",
            "-1000, -567",
            "3, 3",
            "2, 3",
            "3234, 3234"
    })
    void shouldReturnCeiling(int input, Integer expected) {
        addToTree(8, 0, 1, 3234, 3, 8, 12, -567, 123);
        assertEquals(expected, tree.ceil(input));
    }

    @Test
    void shouldReturnNullOnNoCeilingValue() {
        addToTree(8, 0, 1, 3234, 8, 12, -567, 123);
        assertNull(tree.ceil(3235));
    }

    @ParameterizedTest
    @CsvSource({
            "-567, -567",
            "3, 3",
            "2, 1",
            "3234, 3234",
            "3235, 3234"
    })
    void shouldReturnFloor(int input, Integer expected) {
        addToTree(8, 0, 1, 3234, 8, 3, 12, -567, 123);
        assertEquals(expected, tree.floor(input));
    }

    @Test
    void shouldThrowErrorOnNoFloorValue() {
        addToTree(8, 0, 1, 3234, 8, 12, -567, 123);
        assertNull(tree.floor(-568));
    }

    @Test
    void shouldContainValue() {
        addToTree(8, 0, 1, 3234, 8, 12, -567, 123);
        assertTrue(tree.contains(8));
        assertTrue(tree.contains(0));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(3234));
        assertTrue(tree.contains(12));
        assertTrue(tree.contains(-567));
        assertTrue(tree.contains(123));
    }

    @Test
    void shouldNotContainValue() {
        addToTree(8, 0, 1, 3234, 8, 12, -567, 123);
        assertFalse(tree.contains(-1000));
        assertFalse(tree.contains(1000));
        assertFalse(tree.contains(7));
        assertFalse(tree.contains(-1));
    }

    @Test
    void shouldTraverseTreeBfs() {
        // given
        addToTree(8, 0, 1, 3234, 8, 3, 12, -567, 123);
        VisitorToListCollector visitor = new VisitorToListCollector();
        // when
        tree.traverseBfs(visitor);
        // then
        assertIterableEquals(asList(8, 0, 3234, -567, 1, 12, 3, 123), visitor.getItems());
    }

    @Test
    void shouldTraverseTreeInOrder() {
        // given
        addToTree(8, 0, 1, 3234, 8, 3, 12, -567, 123);
        VisitorToListCollector visitor = new VisitorToListCollector();
        // when
        tree.traverseInOrder(visitor);
        // then
        assertIterableEquals(asList(-567, 0, 1, 3, 8, 12, 123, 3234), visitor.getItems());
    }

    @Test
    void shouldTraverseTreePreOrder() {
        // given
        addToTree(8, 0, 1, 3234, 8, 3, 12, -567, 123);
        VisitorToListCollector visitor = new VisitorToListCollector();
        // when
        tree.traversePreOrder(visitor);
        // then
        assertIterableEquals(asList(8, 0, -567, 1, 3, 3234, 12, 123), visitor.getItems());
    }

    @Test
    void shouldTraverseTreePostOrder() {
        // given
        addToTree(8, 0, 1, 3234, 8, 3, 12, -567, 123);
        VisitorToListCollector visitor = new VisitorToListCollector();
        // when
        tree.traversePostOrder(visitor);
        // then
        assertIterableEquals(asList(-567, 3, 1, 0, 123, 12, 3234, 8), visitor.getItems());
    }

    void addToTree(int... values) {
        Arrays.stream(values)
                .forEach(tree::add);
    }
}


class BinarySearchTreeSpec extends TreeSpec {

    public BinarySearchTreeSpec() {
        super(new BinarySearchTree());
    }
}