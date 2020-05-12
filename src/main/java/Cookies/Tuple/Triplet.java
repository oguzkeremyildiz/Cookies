package Cookies.Tuple;/* Created by oguzkeremyildiz on 18.04.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.2
 */

public class Triplet<A, B, C> {
    A a;
    B b;
    C c;
    public Triplet(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
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
    @Override
    public int hashCode() {
        return a.hashCode() ^ b.hashCode() ^ c.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Triplet)) {
            return false;
        }
        Triplet<A, B, C> triplet = (Triplet<A, B, C>) o;
        return this.a.equals(triplet.getA()) && this.b.equals(triplet.getB()) && this.c.equals(triplet.getC());
    }
    @Override
    public String toString() {
        return "[" + a.toString() + ", " + b.toString() + ", " + c.toString() + "]";
    }
}
