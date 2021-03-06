package Cookies.Tuple;/* Created by oguzkeremyildiz on 18.04.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.3
 */

public class Sextet<A, B, C, D, E, F> {
    A a;
    B b;
    C c;
    D d;
    E e;
    F f;
    public Sextet(A a, B b, C c, D d, E e, F f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    public A getA() {
        return a;
    }
    public B getB() {
        return b;
    }
    public C getC() {
        return c;
    }
    public D getD() {
        return d;
    }
    public E getE() {
        return e;
    }
    public F getF() {
        return f;
    }
    @Override
    public int hashCode() {
        return a.hashCode() ^ b.hashCode() ^ c.hashCode() ^ d.hashCode() ^ e.hashCode() ^ f.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sextet)) {
            return false;
        }
        Sextet<A, B, C, D, E, F> sextet = (Sextet<A, B, C, D, E, F>) o;
        return this.a.equals(sextet.getA()) && this.b.equals(sextet.getB()) && this.c.equals(sextet.getC()) && this.d.equals(sextet.getD()) && this.e.equals(sextet.getE()) && this.f.equals(sextet.getF());
    }
    @Override
    public String toString() {
        return "[" + handleA(a) + ", " + handleB(b) + ", " + handleC(c) + ", " + handleD(d) + ", " + handleE(e) + ", " + handleF(f) + "]";
    }
    private String handleA(A current) {
        if (current == null) {
            return "null";
        }
        return current.toString();
    }
    private String handleB(B current) {
        if (current == null) {
            return "null";
        }
        return current.toString();
    }
    private String handleC(C current) {
        if (current == null) {
            return "null";
        }
        return current.toString();
    }
    private String handleD(D current) {
        if (current == null) {
            return "null";
        }
        return current.toString();
    }
    private String handleE(E current) {
        if (current == null) {
            return "null";
        }
        return current.toString();
    }
    private String handleF(F current) {
        if (current == null) {
            return "null";
        }
        return current.toString();
    }
}
