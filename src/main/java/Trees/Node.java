package Trees;/* Created by oguzkeremyildiz on 4.05.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class Node<Symbol> {

    Symbol node;
    Node<Symbol> big;
    Node<Symbol> small;

    public Node(Symbol node, Node<Symbol> small, Node<Symbol> big) {
        this.node = node;
        this.big = big;
        this.small = small;
    }

    public Symbol getNode() {
        return node;
    }

    public void setNode(Node<Symbol> node) {
        this.node = node.getNode();
    }

    public Node<Symbol> big() {
        return big;
    }

    public Node<Symbol> small() {
        return small;
    }
}
