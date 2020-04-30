package Tuples;/* Created by oguzkeremyildiz on 22.04.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.2
 */

public class Octet<A, B, C, D, E, F, G, H> {
    A a;
    B b;
    C c;
    D d;
    E e;
    F f;
    G g;
    H h;
    public Octet(A a, B b, C c, D d, E e, F f, G g, H h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
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
    public G getG() {
        return g;
    }
    public H getH() {
        return h;
    }
    @Override
    public int hashCode() {
        return a.hashCode() ^ b.hashCode() ^ c.hashCode() ^ d.hashCode() ^ e.hashCode() ^ f.hashCode() ^ g.hashCode() ^ h.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Octet)) {
            return false;
        }
        Octet<A, B, C, D, E, F, G, H> octet = (Octet<A, B, C, D, E, F, G, H>) o;
        return this.a.equals(octet.getA()) && this.b.equals(octet.getB()) && this.c.equals(octet.getC()) && this.d.equals(octet.getD()) && this.e.equals(octet.getE()) && this.f.equals(octet.getF()) && this.g.equals(octet.getG()) && this.h.equals(octet.getH());
    }
    @Override
    public String toString() {
        return "[" + a.toString() + ", " + b.toString() + ", " + c.toString() + ", " + d.toString() + ", " + e.toString() + ", " + f.toString() + ", " + g.toString() + ", " + h.toString() + "]";
    }
}
