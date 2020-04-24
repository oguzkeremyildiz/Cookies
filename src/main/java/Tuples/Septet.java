package Tuples;/* Created by oguzkeremyildiz on 22.04.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.1.0
 */

public class Septet<A, B, C, D, E, F, G> {
    A a;
    B b;
    C c;
    D d;
    E e;
    F f;
    G g;
    public Septet(A a, B b, C c, D d, E e, F f, G g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
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
    @Override
    public int hashCode() {
        return a.hashCode() ^ b.hashCode() ^ c.hashCode() ^ d.hashCode() ^ e.hashCode() ^ f.hashCode() ^ g.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Septet)) {
            return false;
        }
        Septet<A, B, C, D, E, F, G> septet = (Septet<A, B, C, D, E, F, G>) o;
        return this.a.equals(septet.getA()) && this.b.equals(septet.getB()) && this.c.equals(septet.getC()) && this.d.equals(septet.getD()) && this.e.equals(septet.getE()) && this.f.equals(septet.getF()) && this.g.equals(septet.getG());
    }
}
