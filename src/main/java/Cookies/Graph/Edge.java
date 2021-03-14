package Cookies.Graph;

/**
 * @author oguzkeremyildiz
 * @version 1.0.4
 */

public class Edge<E> {

    protected final E length;

    public Edge(E length) {
        this.length = length;
    }

    public E getLength() {
        return length;
    }
}
