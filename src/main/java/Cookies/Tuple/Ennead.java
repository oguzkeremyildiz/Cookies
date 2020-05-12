package Cookies.Tuple;/* Created by oguzkeremyildiz on 12.05.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class Ennead<A, B, C, D, E, F, G, H, I> {
    A a;
    B b;
    C c;
    D d;
    E e;
    F f;
    G g;
    H h;
    I i;
    public Ennead(A a, B b, C c, D d, E e, F f, G g, H h, I i) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
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
    public I getI() {
        return i;
    }
    @Override
    public int hashCode() {
        return a.hashCode() ^ b.hashCode() ^ c.hashCode() ^ d.hashCode() ^ e.hashCode() ^ f.hashCode() ^ g.hashCode() ^ h.hashCode() ^ i.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ennead)) {
            return false;
        }
        Ennead<A, B, C, D, E, F, G, H, I> ennead = (Ennead<A, B, C, D, E, F, G, H, I>) o;
        return this.a.equals(ennead.getA()) && this.b.equals(ennead.getB()) && this.c.equals(ennead.getC()) && this.d.equals(ennead.getD()) && this.e.equals(ennead.getE()) && this.f.equals(ennead.getF()) && this.g.equals(ennead.getG()) && this.h.equals(ennead.getH()) && this.i.equals(ennead.getI());
    }
    @Override
    public String toString() {
        return "[" + a.toString() + ", " + b.toString() + ", " + c.toString() + ", " + d.toString() + ", " + e.toString() + ", " + f.toString() + ", " + g.toString() + ", " + h.toString() + ", " + i.toString() + "]";
    }
}
