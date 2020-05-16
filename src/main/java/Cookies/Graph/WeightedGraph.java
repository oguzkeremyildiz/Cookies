package Cookies.Graph;/* Created by oguzkeremyildiz on 11.04.2020 */

import Cookies.Tuple.Pair;

import java.util.*;

/**
 * @author oguzkeremyildiz
 * @version 1.0.1
 */

public class WeightedGraph<Symbol> {
    private HashMap<Symbol, LinkedList<Pair<Symbol, Integer>>> edgeList;
    private HashSet<Symbol> vertexList;

    public WeightedGraph(){
        edgeList = new HashMap<>();
        vertexList = new HashSet<>();
    }

    public WeightedGraph(HashSet<Symbol> vertexList) {
        edgeList = new HashMap<>();
        this.vertexList = vertexList;
    }

    public void clear() {
        edgeList.clear();
        vertexList.clear();
    }

    public boolean isEmpty() {
        return edgeList.isEmpty();
    }

    public boolean containsKey(Symbol element) {
        return edgeList.containsKey(element);
    }

    public boolean containsValue(LinkedList<Pair<Symbol, Integer>> list) {
        return edgeList.containsValue(list);
    }

    public void addDirectedEdge(Symbol from, Symbol to, int length) {
        vertexList.add(from);
        vertexList.add(to);
        if (!edgeList.containsKey(from)){
            edgeList.put(from, new LinkedList<>());
            edgeList.get(from).addFirst(new Pair<>(to, length));
        } else {
            edgeList.get(from).add(new Pair<>(to, length));
        }
    }

    public void addUndirectedEdge(Symbol from, Symbol to, int lengthFrom, int lengthTo) {
        addDirectedEdge(from, to, lengthTo);
        addDirectedEdge(to, from, lengthFrom);
    }

    public Set<Symbol> getKeySet() {
        return edgeList.keySet();
    }

    public int size() {
        return edgeList.size();
    }

    public void put(Symbol index, LinkedList<Pair<Symbol, Integer>> list) {
        vertexList.add(index);
        edgeList.put(index, list);
        if (list.size() > 0) {
            for (Pair<Symbol, Integer> element : list) {
                vertexList.add(element.getKey());
            }
        }
    }

    public void replace(Symbol key, LinkedList<Pair<Symbol, Integer>> value) {
        edgeList.replace(key, value);
        if (value.size() > 0) {
            for (Pair<Symbol, Integer> element : value) {
                vertexList.add(element.getKey());
            }
        }
    }

    public LinkedList<Pair<Symbol, Integer>> get(Symbol index) {
        return edgeList.get(index);
    }

    public Pair<Symbol, Integer> get(Symbol element, int index) {
        return edgeList.get(element).get(index);
    }

    public HashMap<Symbol, Integer> bellmanFord(Symbol edge) {
        HashMap<Symbol, Integer> map = new HashMap<>();
        map.put(edge, 0);
        for (Symbol element : edgeList.keySet()) {
            if (!element.equals(edge)) {
                map.put(element, Integer.MAX_VALUE);
            }
        }
        for (int t = 0; t < edgeList.size() - 1; t++) {
            for (Symbol key : map.keySet()) {
                for (int i = 0; i < this.get(key).size(); i++) {
                    Pair<Symbol, Integer> element = this.get(key).get(i);
                    if (!map.get(key).equals(Integer.MAX_VALUE)) {
                        if (map.get(element.getKey()) > element.getValue() + map.get(key)) {
                            map.put(element.getKey(), element.getValue() + map.get(key));
                        }
                    }
                }
            }
        }
        return map;
    }
}
