package Cookies.Set;/* Created by oguzkeremyildiz on 19.05.2020 */

/**
 * @author olcaytaner
 * @version 1.0.0
 */

public class Set<T> {

    T data;
    int parent;
    int depth;

    public Set(T data, int index) {
        this.data = data;
        parent = index;
        depth = 1;
    }
}
