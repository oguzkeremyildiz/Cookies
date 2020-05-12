package Cookies.Tuple;/* Created by oguzkeremyildiz on 18.04.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.2
 */

public class Quintet<A, B, C, D, E> {
    A a;
    B b;
    C c;
    D d;
    E e;
    public Quintet(A a, B b, C c, D d, E e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
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
    @Override
    public int hashCode() {
        return a.hashCode() ^ b.hashCode() ^ c.hashCode() ^ d.hashCode() ^ e.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Quintet)) {
            return false;
        }
        Quintet<A, B, C, D, E> quintet = (Quintet<A, B, C, D, E>) o;
        return this.a.equals(quintet.getA()) && this.b.equals(quintet.getB()) && this.c.equals(quintet.getC()) && this.d.equals(quintet.getD()) && this.e.equals(quintet.getE());
    }
    @Override
    public String toString() {
        return "[" + a.toString() + ", " + b.toString() + ", " + c.toString() + ", " + d.toString() + ", " + e.toString() + "]";
    }
}
