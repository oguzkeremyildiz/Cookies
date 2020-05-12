package Cookies.Tuple;/* Created by oguzkeremyildiz on 12.05.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class Decade<A, B, C, D, E, F, G, H, I, J> {
    A a;
    B b;
    C c;
    D d;
    E e;
    F f;
    G g;
    H h;
    I i;
    J j;
    public Decade(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
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
    public J getJ() {
        return j;
    }
    @Override
    public int hashCode() {
        return a.hashCode() ^ b.hashCode() ^ c.hashCode() ^ d.hashCode() ^ e.hashCode() ^ f.hashCode() ^ g.hashCode() ^ h.hashCode() ^ i.hashCode() ^ j.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Decade)) {
            return false;
        }
        Decade<A, B, C, D, E, F, G, H, I, J> decade = (Decade<A, B, C, D, E, F, G, H, I, J>) o;
        return this.a.equals(decade.getA()) && this.b.equals(decade.getB()) && this.c.equals(decade.getC()) && this.d.equals(decade.getD()) && this.e.equals(decade.getE()) && this.f.equals(decade.getF()) && this.g.equals(decade.getG()) && this.h.equals(decade.getH()) && this.i.equals(decade.getI()) && this.j.equals(decade.getJ());
    }
    @Override
    public String toString() {
        return "[" + a.toString() + ", " + b.toString() + ", " + c.toString() + ", " + d.toString() + ", " + e.toString() + ", " + f.toString() + ", " + g.toString() + ", " + h.toString() + ", " + i.toString() + ", " + j.toString() + "]";
    }
}
