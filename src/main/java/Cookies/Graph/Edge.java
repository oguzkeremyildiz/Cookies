package Cookies.Graph;

/**
 * @author oguzkeremyildiz
 * @version 1.0.5
 */

public class Edge<E> {

    protected final E length;

    public Edge(E length) {
        this.length = length;
    }

    public E getLength() {
        return length;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) {
            return false;
        }
        Edge<E> edge = (Edge<E>) obj;
        return this.length.equals(edge.length);
    }

    @Override
    public String toString() {
        return "[length = " + length.toString() + "]";
    }
}
