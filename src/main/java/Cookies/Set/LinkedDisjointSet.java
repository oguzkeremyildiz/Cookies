package Cookies.Set;/* Created by oguzkeremyildiz on 19.05.2020 */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author oguzkeremyildiz
 * @version 1.0.1
 */

public class LinkedDisjointSet<T> {

    Set<T>[] sets;
    int count;
    private LinkedHashMap<T, Integer> map;
    private int size;

    public LinkedDisjointSet(T[] elements) {
        size = elements.length;
        map = new LinkedHashMap<>();
        sets = new Set[elements.length];
        count = elements.length;
        for (int i = 0; i < elements.length; i++) {
            map.put(elements[i], i);
            sets[i] = new Set<>(elements[i], i);
        }
    }

    public LinkedDisjointSet(LinkedList<T> elements) {
        size = elements.size();
        map = new LinkedHashMap<>();
        sets = new Set[elements.size()];
        count = elements.size();
        for (int i = 0; i < elements.size(); i++) {
            map.put(elements.get(i), i);
            sets[i] = new Set<>(elements.get(i), i);
        }
    }

    public LinkedDisjointSet(ArrayList<T> elements) {
        size = elements.size();
        map = new LinkedHashMap<>();
        sets = new Set[elements.size()];
        count = elements.size();
        for (int i = 0; i < elements.size(); i++) {
            map.put(elements.get(i), i);
            sets[i] = new Set<>(elements.get(i), i);
        }
    }

    public int size() {
        return size;
    }

    public int findSet(int index) {
        if (sets[index].parent != index) {
            return findSet(sets[index].parent);
        }
        return sets[index].parent;
    }

    public int findSet(T type) {
        if (sets[map.get(type)].parent != map.get(type)) {
            return findSet(sets[map.get(type)].parent);
        }
        return sets[map.get(type)].parent;
    }

    public void union(int index1, int index2) {
        int x;
        int y;
        x = findSet(index1);
        y = findSet(index2);
        if (sets[x].depth < sets[y].depth) {
            sets[x].parent = y;
        } else {
            sets[y].parent = x;
            if (sets[x].depth == sets[y].depth) {
                sets[x].depth++;
            }
        }
    }

    public void union(T element1, T element2) {
        int x;
        int y;
        x = findSet(element1);
        y = findSet(element2);
        if (sets[x].depth < sets[y].depth) {
            sets[x].parent = y;
        } else {
            sets[y].parent = x;
            if (sets[x].depth == sets[y].depth) {
                sets[x].depth++;
            }
        }
    }
}
