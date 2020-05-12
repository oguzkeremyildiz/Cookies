package Cookies.Tuple;/* Created by oguzkeremyildiz on 18.04.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.2
 */

public class Unit<A> {
    A a;
    public Unit(A a) {
        this.a = a;
    }
    public A getA() {
        return a;
    }
    @Override
    public int hashCode() {
        return a.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Unit)) {
            return false;
        }
        Unit<A> unit = (Unit<A>) o;
        return this.a.equals(unit.a);
    }
    @Override
    public String toString() {
        return "[" + a.toString() + "]";
    }
}
