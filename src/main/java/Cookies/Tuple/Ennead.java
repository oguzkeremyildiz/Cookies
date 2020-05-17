package Cookies.Tuple;/* Created by oguzkeremyildiz on 12.05.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.1
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
        return "[" + handleA(a) + ", " + handleB(b) + ", " + handleC(c) + ", " + handleD(d) + ", " + handleE(e) + ", " + handleF(f) + ", " + handleG(g) + ", " + handleH(h) + ", " + handleI(i) + "]";
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
    private String handleG(G current) {
        if (current == null) {
            return "null";
        }
        return current.toString();
    }
    private String handleH(H current) {
        if (current == null) {
            return "null";
        }
        return current.toString();
    }
    private String handleI(I current) {
        if (current == null) {
            return "null";
        }
        return current.toString();
    }
}
