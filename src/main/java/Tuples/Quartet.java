package Tuples;/* Created by oguzkeremyildiz on 18.04.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.1
 */

public class Quartet<A, B, C, D> {
    A a;
    B b;
    C c;
    D d;
    public Quartet(A a, B b, C c, D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
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
    @Override
    public int hashCode() {
        return a.hashCode() ^ b.hashCode() ^ c.hashCode() ^ d.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Quartet)) {
            return false;
        }
        Quartet<A, B, C, D> quartet = (Quartet<A, B, C, D>) o;
        return this.a.equals(quartet.getA()) && this.b.equals(quartet.getB()) && this.c.equals(quartet.getC()) && this.d.equals(quartet.getD());
    }
    @Override
    public String toString() {
        return a.toString() + ", " + b.toString() + ", " + c.toString() + ", " + d.toString();
    }
}
