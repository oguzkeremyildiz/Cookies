package Cookies.Graph;

/**
 * @author oguzkeremyildiz
 * @version 1.0.3
 */

public class Edge<E> {

    private final E capacity;
    private E flow;
    private E residual;
    private LengthInterface<E> lengthInterface;

    public Edge(E capacity) {
        this.capacity = capacity;
        this.flow = null;
        this.residual = null;
    }

    public Edge(E capacity, LengthInterface<E> lengthInterface) {
        this.capacity = capacity;
        this.lengthInterface = lengthInterface;
        flow = lengthInterface.min();
        residual = capacity;
    }

    public Edge(E capacity, E residual, LengthInterface<E> lengthInterface) {
        this.capacity = capacity;
        this.residual = residual;
        flow = lengthInterface.remove(capacity, residual);
        this.lengthInterface = lengthInterface;
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

    public void setFlow(E flow) {
        this.flow = flow;
        setResidual(lengthInterface.remove(getCapacity(), getFlow()));
    }

    private void setResidual(E residual) {
        this.residual = residual;
    }
}
