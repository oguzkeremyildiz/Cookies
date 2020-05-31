package Cookies.Graph;

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class Edge<E> {

    private E capacity;
    private E flow;
    private E residual;

    public Edge(E capacity, E flow, E residual) {
        this.capacity = capacity;
        this.flow = flow;
        this.residual = residual;
    }

    public E getCapacity() {
        return capacity;
    }

    public E getFlow() {
        return flow;
    }

    public E getResidual() {
        return residual;
    }
}
