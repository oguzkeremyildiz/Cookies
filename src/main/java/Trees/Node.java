package Trees;/* Created by oguzkeremyildiz on 4.05.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class Node<Symbol> {

    Symbol symbol;
    Node<Symbol> parent;
    Node<Symbol> right;
    Node<Symbol> left;

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

    public Node<Symbol> big() {
        return right;
    }

    public Node<Symbol> small() {
        return left;
    }
}
