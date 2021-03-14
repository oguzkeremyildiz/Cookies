package Cookies.Graph;/* Created by oguzkeremyildiz on 14.03.2021 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class ResidualEdge<E> extends Edge<E> {

    private E flow;
    private E residual;
    private LengthInterface<E> lengthInterface;

    public ResidualEdge(E length, LengthInterface<E> lengthInterface) {
        super(length);
        this.lengthInterface = lengthInterface;
        flow = lengthInterface.min();
        residual = length;
    }

    public ResidualEdge(E length, E residual, LengthInterface<E> lengthInterface) {
        super(length);
        this.residual = residual;
        flow = lengthInterface.remove(length, residual);
        this.lengthInterface = lengthInterface;
    }

    public E getFlow() {
        return flow;
    }

    public E getResidual() {
        return residual;
    }

    public void setFlow(E flow) {
        this.flow = flow;
        setResidual(lengthInterface.remove(getLength(), getFlow()));
    }

    private void setResidual(E residual) {
        this.residual = residual;
    }
}
