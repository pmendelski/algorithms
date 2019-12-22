package com.coditory.sandbox.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

import static com.coditory.sandbox.tree.Tree.Median.singleValueMedian;
import static com.coditory.sandbox.tree.Tree.Median.twoValuesMedian;

public class BinarySearchTree implements Tree {
    class Node {
        final int value;
        int size = 1;
        Node left;
        Node right;

        Node(int value) {
            this(value, null, null);
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        void add(int value) {
            if (this.value == value) return;
            if (value > this.value) {
                if (this.right == null) {
                    this.right = new Node(value);
                } else {
                    this.right.add(value);
                }
            } else {
                if (this.left == null) {
                    this.left = new Node(value);
                }
                this.left.add(value);
            }
            updateSize();
        }

        private void updateSize() {
            int leftSize = left != null ? left.size : 0;
            int rightSize = right != null ? right.size : 0;
            size = 1 + leftSize + rightSize;
        }

        int min() {
            if (left == null) {
                return value;
            }
            return left.min();
        }

        int max() {
            if (right == null) {
                return value;
            }
            return right.max();
        }

        Integer floor(int value) {
            if (this.value == value) return value;
            Integer result = null;
            if (this.value < value) {
                if (right != null) {
                    Integer rightFloor = right.floor(value);
                    result = rightFloor != null
                            ? rightFloor
                            : this.value;
                } else {
                    result = this.value;
                }
            } else if (left != null) {
                result = left.floor(value);
            }
            return result;
        }

        Integer ceil(int value) {
            if (this.value == value) return value;
            Integer result = null;
            if (this.value > value) {
                if (left != null) {
                    Integer leftCeil = left.ceil(value);
                    result = leftCeil != null
                            ? leftCeil
                            : this.value;
                } else {
                    result = this.value;
                }
            } else if (right != null) {
                result = right.ceil(value);
            }
            return result;
        }

        Node find(int value) {
            if (this.value == value) return this;
            if (value < this.value && left != null) {
                return left.find(value);
            }
            if (right != null) {
                return right.find(value);
            }
            return null;
        }

        Integer rank(int value, int smallerValues) {
            int leftSize = left != null ? left.size : 0;
            if (this.value == value) return smallerValues + leftSize;
            if (value < this.value && left != null) {
                return left.rank(value, smallerValues);
            } else if (right != null) {
                return right.rank(value, 1 + leftSize + smallerValues);
            }
            return null;
        }

        Node findByRank(int rank, int smaller) {
            int nodeRank = this.rank(smaller);
            if (nodeRank == rank) return this;
            if (rank < nodeRank && left != null) {
                return left.findByRank(rank, smaller);
            }
            if (rank > nodeRank && right != null) {
                return right.findByRank(rank, nodeRank + 1);
            }
            return null;
        }

        int rank(int smallerValues) {
            int leftSize = left != null ? left.size : 0;
            return leftSize + smallerValues;
        }

        void traverseInOrder(Visitor visitor) {
            if (left != null) {
                left.traverseInOrder(visitor);
            }
            visitor.visit(this.value);
            if (right != null) {
                right.traverseInOrder(visitor);
            }
        }

        void traversePreOrder(Visitor visitor) {
            visitor.visit(this.value);
            if (left != null) {
                left.traversePreOrder(visitor);
            }
            if (right != null) {
                right.traversePreOrder(visitor);
            }
        }

        void traversePostOrder(Visitor visitor) {
            if (left != null) {
                left.traversePostOrder(visitor);
            }
            if (right != null) {
                right.traversePostOrder(visitor);
            }
            visitor.visit(this.value);
        }
    }

    private Node root;

    @Override
    public void add(int value) {
        if (root == null) {
            this.root = new Node(value);
        } else {
            root.add(value);
        }
    }

    @Override
    public void traverseInOrder(Visitor visitor) {
        if (root == null) return;
        root.traverseInOrder(visitor);
    }

    @Override
    public void traversePreOrder(Visitor visitor) {
        if (root == null) return;
        root.traversePreOrder(visitor);
    }

    @Override
    public void traversePostOrder(Visitor visitor) {
        if (root == null) return;
        root.traversePostOrder(visitor);
    }

    @Override
    public void traverseBfs(Visitor visitor) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visitor.visit(node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    @Override
    public Integer min() {
        return onRootOrNull(Node::min);
    }

    @Override
    public Integer max() {
        return onRootOrNull(Node::max);
    }

    @Override
    public Integer floor(int value) {
        return onRootOrNull(root -> root.floor(value));
    }

    @Override
    public Integer ceil(int value) {
        return onRootOrNull(root -> root.ceil(value));
    }

    @Override
    public Integer rank(int value) {
        return onRootOrNull(root -> root.rank(value, 0));
    }

    @Override
    public boolean contains(int value) {
        return onRootOrNull(root -> root.find(value)) != null;
    }

    @Override
    public Median median() {
        if (this.root == null) return null;
        if (this.root.size % 2 == 0) {
            int mid = this.root.size / 2;
            Node firstNode = this.root.findByRank(mid - 1, 0);
            Node secondNode = this.root.findByRank(mid, 0);
            return twoValuesMedian(firstNode.value, secondNode.value);
        }
        int mid = this.root.size / 2;
        Node node = this.root.findByRank(mid, 0);
        return singleValueMedian(node.value);
    }

    private <T> T onRootOrNull(Function<Node, T> action) {
        if (root == null) return null;
        return action.apply(root);
    }
}

