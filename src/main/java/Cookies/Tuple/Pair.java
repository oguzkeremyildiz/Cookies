package Cookies.Tuple;/* Created by oguzkeremyildiz on 17.04.2020 */

/**
 * @author oguzkeremyildiz
 * @version 1.0.3
 */

public class Pair<K, V> {
    K key;
    V value;
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
    @Override
    public int hashCode() {
        return key.hashCode() ^ value.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<K, V> pair = (Pair<K, V>) o;
        return this.key.equals(pair.getKey()) && this.value.equals(pair.getValue());
    }
    @Override
    public String toString() {
        return "[" + handleK(key) + ", " + handleV(value) + "]";
    }
    private String handleK(K current) {
        if (current == null) {
            return "null";
        }
        return current.toString();
    }
    private String handleV(V current) {
        if (current == null) {
            return "null";
        }
        return current.toString();
    }
}
