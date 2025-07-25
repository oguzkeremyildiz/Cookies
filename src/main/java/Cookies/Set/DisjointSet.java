package Cookies.Set;/* Created by oguzkeremyildiz on 19.05.2020 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author olcaytaner
 * @author oguzkeremyildiz
 * @version 1.0.1
 */

public class DisjointSet<T> {

   Set<T>[] sets;
   int count;
   private HashMap<T, Integer> map;
   private int size;

   public DisjointSet(ArrayList<T> elements) {
       size = elements.size();
       map = new HashMap<>();
       sets = new Set[elements.size()];
       count = elements.size();
       for (int i = 0; i < elements.size(); i++) {
           map.put(elements.get(i), i);
           sets[i] = new Set<>(elements.get(i), i);
       }
   }

    public DisjointSet(LinkedList<T> elements) {
       size = elements.size();
        map = new HashMap<>();
        sets = new Set[elements.size()];
        count = elements.size();
        for (int i = 0; i < elements.size(); i++) {
            map.put(elements.get(i), i);
            sets[i] = new Set<>(elements.get(i), i);
        }
    }

   public DisjointSet(T[] elements) {
       size = elements.length;
       map = new HashMap<>();
       sets = new Set[elements.length];
       count = elements.length;
       for (int i = 0; i < elements.length; i++) {
           map.put(elements[i], i);
           sets[i] = new Set<>(elements[i], i);
       }
   }

   public int size() {
       return size;
   }

   public int findSetByIndex(int index) {
       if (sets[index].parent != index) {
           return findSetByIndex(sets[index].parent);
       }
       return sets[index].parent;
   }

   public int findSet(T type) {
       if (sets[map.get(type)].parent != map.get(type)) {
           return findSetByIndex(sets[map.get(type)].parent);
       }
       return sets[map.get(type)].parent;
   }

   public void unionByIndex(int index1, int index2) {
       int x;
       int y;
       x = findSetByIndex(index1);
       y = findSetByIndex(index2);
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
