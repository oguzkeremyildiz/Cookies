package Cookies.Tree;/* Created by oguzkeremyildiz on 4.05.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.1
 */

public class Node<Symbol> {

    private Symbol symbol;
    private Node<Symbol> parent;
    private Node<Symbol> right;
    private Node<Symbol> left;

    public Node(Symbol symbol, Node<Symbol> left, Node<Symbol> right, Node<Symbol> parent) {
        this.symbol = symbol;
        this.right = right;
        this.left = left;
        this.parent = parent;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Node<Symbol> getParent() {
        return parent;
    }

    public void setParent(Node<Symbol> parent) {
        this.parent = parent;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Node<Symbol> getRight() {
        return right;
    }

    public Node<Symbol> getLeft() {
        return left;
    }

    public void setLeft(Node<Symbol> left) {
        this.left = left;
    }

    public void setRight(Node<Symbol> right) {
        this.right = right;
    }
}
