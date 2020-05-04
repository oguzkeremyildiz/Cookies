package Trees;/* Created by oguzkeremyildiz on 4.05.2020 */

import java.util.Comparator;
import java.util.HashSet;

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class BinaryTree<Symbol> {

    private Node<Symbol> root;
    private HashSet<Symbol> nodeSet;
    private Comparator<Symbol> comparator;
    private Node<Symbol> biggest;

    public BinaryTree(Node<Symbol> root, Comparator<Symbol> comparator) {
        this.root = root;
        nodeSet = new HashSet<>();
        nodeSet.add(root.getNode());
        this.comparator = comparator;
    }

    public boolean containsNode(Node<Symbol> node) {
        return nodeSet.contains(node.getNode());
    }

    public void addNode(Symbol no) {
        if (!nodeSet.contains(no)) {
            nodeSet.add(no);
            boolean find = false;
            Node<Symbol> current = root;
            while (!find) {
                if (comparator.compare(current.getNode(), no) > 0) {
                    if (current.small == null) {
                        current.small = new Node<>(no, null, null);
                        find = true;
                    } else {
                        current = current.small;
                    }
                } else {
                    if (current.big == null) {
                        current.big = new Node<>(no, null, null);
                        find = true;
                    } else {
                        current = current.big;
                    }
                }
            }
        }
    }

    public void addNode(Node<Symbol> node) {
        if (!containsNode(node)) {
            nodeSet.add(node.getNode());
            boolean find = false;
            Node<Symbol> current = root;
            while (!find) {
                if (comparator.compare(current.getNode(), node.getNode()) > 0) {
                    if (current.small == null) {
                        current.small = node;
                        find = true;
                    } else {
                        current = current.small;
                    }
                } else {
                    if (current.big == null) {
                        current.big = node;
                        find = true;
                    } else {
                        current = current.big;
                    }
                }
            }
        }
    }

    public void removeNode(Symbol no) {
        Node<Symbol> node = findNode(no);
        biggest = null;
        if (containsNode(node)) {
            if (!root.equals(node)) {
                if (node.big == null && node.small == null) {
                    Node<Symbol> current = root;
                    Node<Symbol> old = null;
                    while (true) {
                        if (comparator.compare(current.getNode(), node.getNode()) > 0) {
                            old = current;
                            current = current.small;
                        } else if (comparator.compare(current.getNode(), node.getNode()) < 0) {
                            old = current;
                            current = current.big;
                        } else {
                            nodeSet.remove(current.getNode());
                            if (old.small.equals(current)) {
                                old.small = null;
                            } else {
                                old.big = null;
                            }
                            break;
                        }
                    }
                } else if (node.big != null && node.small != null){
                    nodeSet.remove(no);
                    Node<Symbol> temporary = findBiggestNear(node);
                    removeNode(findBiggestNear(node).getNode());
                    node.setNode(temporary);
                } else {
                    Node<Symbol> current = root;
                    Node<Symbol> old = null;
                    while (true) {
                        if (comparator.compare(current.getNode(), node.getNode()) > 0) {
                            old = current;
                            current = current.small;
                        } else if (comparator.compare(current.getNode(), node.getNode()) < 0) {
                            old = current;
                            current = current.big;
                        } else {
                            break;
                        }
                    }
                    if (node.big != null) {
                        if (old.big.equals(node)) {
                            old.big = node.big;
                        } else {
                            old.small = node.big;
                        }
                    } else {
                        if (old.big.equals(node)) {
                            old.big = node.small;
                        } else {
                            old.small = node.small;
                        }
                    }
                    nodeSet.remove(no);
                }
            } else {
                if (root.small != null) {
                    Node<Symbol> current = root.small;
                    while (current.big != null) {
                        current = current.big;
                    }
                    Node<Symbol> temporary = current;
                    removeNode(current.getNode());
                    root.setNode(temporary);
                } else if (root.big != null) {
                    Node<Symbol> current = root.big;
                    while (current.small != null) {
                        current = current.small;
                    }
                    Node<Symbol> temporary = current;
                    removeNode(current.getNode());
                    root.setNode(temporary);
                }
            }
        }
    }

    private Node<Symbol> findBiggestNear(Node<Symbol> node) {
        search(node, node.getNode(), null);
        return biggest;
    }

    private void search(Node<Symbol> currentNode, Symbol max, Node<Symbol> biggestNear) {
        Symbol current;
        if (biggestNear == null) {
            current = null;
        } else {
            current = biggestNear.getNode();
        }
        if (comparator.compare(currentNode.getNode(), max) < 0) {
            if (current == null) {
                current = currentNode.getNode();
                biggestNear = currentNode;
                biggest = biggestNear;
            } else {
                if (comparator.compare(currentNode.getNode(), current) > 0) {
                    current = currentNode.getNode();
                    biggestNear = currentNode;
                    if (comparator.compare(biggest.getNode(), biggestNear.getNode()) < 0) {
                        biggest = biggestNear;
                    }
                }
            }
        }
        if (currentNode.big != null) {
            search(currentNode.big, max, biggestNear);
        }
        if (currentNode.small != null) {
            search(currentNode.small, max, biggestNear);
        }
    }

    private Node<Symbol> findNode(Symbol no) {
        if (root.getNode().equals(no)) {
            return root;
        } else {
            Node<Symbol> current = root;
            while (true) {
                if (comparator.compare(current.getNode(), no) > 0) {
                    current = current.small;
                } else if (comparator.compare(current.getNode(), no) < 0) {
                    current = current.big;
                } else {
                    return current;
                }
            }
        }
    }
}
