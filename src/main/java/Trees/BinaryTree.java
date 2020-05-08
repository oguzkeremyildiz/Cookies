package Trees;/* Created by oguzkeremyildiz on 4.05.2020 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author oguzkeremyildiz
 * @version 1.0.2
 */

public class BinaryTree<Symbol> {

    private Node<Symbol> root;
    private Comparator<Symbol> comparator;

    public BinaryTree(Node<Symbol> root, Comparator<Symbol> comparator) {
        this.root = root;
        this.comparator = comparator;
    }

    public boolean containsNode(Node<Symbol> node) {
        return containsNode(node.getSymbol());
    }

    public boolean containsNode(Symbol no) {
        if (root.getSymbol().equals(no)) {
            return true;
        } else {
            return findNode(no) != null;
        }
    }

    public void addNode(Symbol no) {
        if (!containsNode(no)) {
            boolean find = false;
            Node<Symbol> current = root;
            while (!find) {
                if (comparator.compare(current.getSymbol(), no) > 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(new Node<>(no, null, null, current));
                        find = true;
                    } else {
                        current = current.getLeft();
                    }
                } else {
                    if (current.getRight() == null) {
                        current.setRight(new Node<>(no, null, null, current));
                        find = true;
                    } else {
                        current = current.getRight();
                    }
                }
            }
        }
    }

    public void addNode(Node<Symbol> node) {
        addNode(node.getSymbol());
    }

    public void removeAndSetSymbol(Node<Symbol> node, Node<Symbol> replacement){
        Symbol symbol = replacement.getSymbol();
        removeNode(symbol);
        node.setSymbol(symbol);
    }

    public void removeNode(Node<Symbol> node) {
        removeNode(node.getSymbol());
    }

    public void removeNode(Symbol no) {
        Node<Symbol> node = findNode(no);
        if (!root.equals(node)) {
            if (node.getRight() == null && node.getLeft() == null) {
                Node<Symbol> parent = node.getParent();
                if (parent.getLeft() != null && parent.getLeft().equals(node)) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            } else if (node.getRight() != null && node.getLeft() != null) {
                Node<Symbol> replacement = findBiggestNearForTwoChildren(node);
                removeAndSetSymbol(node, replacement);
            } else {
                if (node.getLeft() != null) {
                    Node<Symbol> replacement = searchForSmall(node.getLeft());
                    removeAndSetSymbol(node, replacement);
                } else {
                    Node<Symbol> replacement = searchForBig(node.getRight());
                    removeAndSetSymbol(node, replacement);
                }
            }
        } else {
            if (root.getLeft() != null) {
                Node<Symbol> replacement = searchForSmall(root.getLeft());
                removeAndSetSymbol(root, replacement);
            } else if (root.getRight() != null) {
                Node<Symbol> replacement = searchForBig(root.getRight());
                removeAndSetSymbol(root, replacement);
            } else {
                root = null;
            }
        }
    }

    private Node<Symbol> findBiggestNearForTwoChildren(Node<Symbol> node) {
        Random random = new Random();
        if (random.nextBoolean()) {
            return searchForBig(node.getRight());
        } else {
            return searchForSmall(node.getLeft());
        }
    }

    private Node<Symbol> searchForBig(Node<Symbol> currentNode) {
        Node<Symbol> returning = currentNode;
        while (returning.getLeft() != null) {
            returning = returning.getLeft();
        }
        return returning;
    }

    private Node<Symbol> searchForSmall(Node<Symbol> currentNode) {
        Node<Symbol> returning = currentNode;
        while (returning.getRight() != null) {
            returning = returning.getRight();
        }
        return returning;
    }

    private Node<Symbol> findNode(Node<Symbol> node) {
        return findNode(node.getSymbol());
    }

    private Node<Symbol> findNode(Symbol no) {
        if (root.getSymbol().equals(no)) {
            return root;
        } else {
            Node<Symbol> current = root;
            while (true) {
                if (no != null) {
                    if (comparator.compare(current.getSymbol(), no) > 0) {
                        if (current.getLeft() != null) {
                            current = current.getLeft();
                        } else {
                            break;
                        }
                    } else if (comparator.compare(current.getSymbol(), no) < 0) {
                        if (current.getRight() != null) {
                            current = current.getRight();
                        } else {
                            break;
                        }
                    } else {
                        return current;
                    }
                } else {
                    break;
                }
            }
        }
        return null;
    }

    public void print() {
        LinkedList<Symbol> list = new LinkedList<>();
        list.add(root.getSymbol());
        depthFirstSearch(root, list);
        System.out.println(list);
    }

    private void depthFirstSearch(Node<Symbol> node, LinkedList<Symbol> list) {
        if (node.getLeft() != null) {
            list.add(node.getLeft().getSymbol());
            depthFirstSearch(node.getLeft(), list);
        }
        if (node.getRight() != null) {
            list.add(node.getRight().getSymbol());
            depthFirstSearch(node.getRight(), list);
        }
    }
}
