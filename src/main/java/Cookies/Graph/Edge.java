package Cookies.Graph;

/**
 * @author oguzkeremyildiz
 * @version 1.0.1
 */

public class Edge<E> {

    private E capacity;
    private E flow;
    private E residual;
    private LengthInterface<E> lengthInterface;

    public Edge(E capacity, LengthInterface<E> lengthInterface) {
        this.capacity = capacity;
        this.lengthInterface = lengthInterface;
        flow = lengthInterface.min();
        residual = capacity;
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
