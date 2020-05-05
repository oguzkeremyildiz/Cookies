package Trees;/* Created by oguzkeremyildiz on 4.05.2020 */

import java.util.Comparator;
import java.util.Random;

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
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
                    if (current.left == null) {
                        current.left = new Node<>(no, null, null, current);
                        find = true;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = new Node<>(no, null, null, current);
                        find = true;
                    } else {
                        current = current.right;
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

    public void removeNode(Symbol no) {
        Node<Symbol> node = findNode(no);
        if (!root.equals(node)) {
            if (node.right == null && node.left == null) {
                Node<Symbol> parent = node.parent;
                if (parent.left != null && parent.left.equals(node)) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if (node.right != null && node.left != null) {
                Node<Symbol> replacement = findBiggestNearForTwoChildren(node);
                removeAndSetSymbol(node, replacement);
            } else {
                if (node.left != null) {
                    Node<Symbol> replacement = searchForSmall(node.left);
                    removeAndSetSymbol(node, replacement);
                } else {
                    Node<Symbol> replacement = searchForBig(node.right);
                    removeAndSetSymbol(node, replacement);
                }
            }
        } else {
            if (root.left != null) {
                Node<Symbol> replacement = searchForSmall(root.left);
                removeAndSetSymbol(root, replacement);
            } else if (root.right != null) {
                Node<Symbol> replacement = searchForBig(root.right);
                removeAndSetSymbol(root, replacement);
            } else {
                root = null;
            }
        }
    }

    private Node<Symbol> findBiggestNearForTwoChildren(Node<Symbol> node) {
        Random random = new Random();
        if (random.nextBoolean()) {
            return searchForBig(node.right);
        } else {
            return searchForSmall(node.left);
        }
    }

    private Node<Symbol> searchForBig(Node<Symbol> currentNode) {
        Node<Symbol> returning = currentNode;
        while (returning.left != null) {
            returning = returning.left;
        }
        return returning;
    }

    private Node<Symbol> searchForSmall(Node<Symbol> currentNode) {
        Node<Symbol> returning = currentNode;
        while (returning.right != null) {
            returning = returning.right;
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
                        if (current.left != null) {
                            current = current.left;
                        } else {
                            break;
                        }
                    } else if (comparator.compare(current.getSymbol(), no) < 0) {
                        if (current.right != null) {
                            current = current.right;
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
}
