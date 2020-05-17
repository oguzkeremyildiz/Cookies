package Cookies.Graph;/* Created by oguzkeremyildiz on 27.04.2020 */

import Cookies.Tuple.Pair;

import java.util.*;

/**
 * @author oguzkeremyildiz
 * @version 1.0.4
 */

public class LinkedWeightedGraph<Symbol> {
    private LinkedHashMap<Symbol, LinkedList<Pair<Symbol, Integer>>> edgeList;
    private LinkedHashSet<Symbol> vertexList;

    public LinkedWeightedGraph() {
        edgeList = new LinkedHashMap<>();
        vertexList = new LinkedHashSet<>();
    }

    public LinkedWeightedGraph(LinkedHashSet<Symbol> vertexList) {
        edgeList = new LinkedHashMap<>();
        this.vertexList = vertexList;
    }

    public void clear() {
        edgeList.clear();
        vertexList.clear();
    }

    public LinkedList<Pair<Symbol, Integer>> getFirst() {
        Map.Entry<Symbol, LinkedList<Pair<Symbol, Integer>>> entry = edgeList.entrySet().iterator().next();
        Symbol key = entry.getKey();
        return edgeList.get(key);
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

    public LinkedHashMap<Symbol, Pair<Integer, Symbol>> bellmanFord(Symbol edge) {
        LinkedHashMap<Symbol, Pair<Integer, Symbol>> map = new LinkedHashMap<>();
        map.put(edge, new Pair<>(0, edge));
        for (Symbol element : vertexList) {
            if (!element.equals(edge)) {
                map.put(element, new Pair<>(Integer.MAX_VALUE, null));
            }
        }
        for (int t = 0; t < vertexList.size() - 1; t++) {
            for (Symbol key : map.keySet()) {
                if (this.containsKey(key)) {
                    for (int i = 0; i < this.get(key).size(); i++) {
                        Pair<Symbol, Integer> element = this.get(key).get(i);
                        if (!map.get(key).getKey().equals(Integer.MAX_VALUE)) {
                            if (map.get(element.getKey()).getKey() > element.getValue() + map.get(key).getKey()) {
                                map.put(element.getKey(), new Pair<>(element.getValue() + map.get(key).getKey(), key));
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    public int[][] floydWarshall() {
        int[][] array = new int[vertexList.size()][vertexList.size()];
        int current;
        LinkedHashMap<Symbol, Integer> map = new LinkedHashMap<>();
        int i = -1;
        for (Symbol key : vertexList) {
            i++;
            map.put(key, i);
        }
        for (int j = 0; j < vertexList.size(); j++) {
            for (int k = 0; k < vertexList.size(); k++) {
                if (j == k) {
                    array[j][k] = 0;
                } else {
                    array[j][k] = Integer.MAX_VALUE;
                }
            }
        }
        for (Symbol key : this.getKeySet()) {
            for (int k = 0; k < this.get(key).size(); k++) {
                array[map.get(key)][map.get(this.get(key).get(k).getKey())] = this.get(key).get(k).getValue();
            }
        }
        for (int j = 0; j < vertexList.size(); j++) {
            for (int k = 0; k < vertexList.size(); k++) {
                for (int l = 0; l < vertexList.size(); l++) {
                    if (array[k][j] != Integer.MAX_VALUE && array[j][l] != Integer.MAX_VALUE) {
                        current = array[k][j] + array[j][l];
                        if (current < array[k][l]) {
                            array[k][l] = current;
                        }
                    }
                }
            }
        }
        return array;
    }

    public Pair<LinkedHashMap<Integer, Symbol>, int[][]> floydWarshallWithKeys() {
        int[][] array = new int[vertexList.size()][vertexList.size()];
        int current;
        LinkedHashMap<Symbol, Integer> map = new LinkedHashMap<>();
        int i = -1;
        for (Symbol key : vertexList) {
            i++;
            map.put(key, i);
        }
        for (int j = 0; j < vertexList.size(); j++) {
            for (int k = 0; k < vertexList.size(); k++) {
                if (j == k) {
                    array[j][k] = 0;
                } else {
                    array[j][k] = Integer.MAX_VALUE;
                }
            }
        }
        for (Symbol key : this.getKeySet()) {
            for (int k = 0; k < this.get(key).size(); k++) {
                array[map.get(key)][map.get(this.get(key).get(k).getKey())] = this.get(key).get(k).getValue();
            }
        }
        for (int j = 0; j < vertexList.size(); j++) {
            for (int k = 0; k < vertexList.size(); k++) {
                for (int l = 0; l < vertexList.size(); l++) {
                    if (array[k][j] != Integer.MAX_VALUE && array[j][l] != Integer.MAX_VALUE) {
                        current = array[k][j] + array[j][l];
                        if (current < array[k][l]) {
                            array[k][l] = current;
                        }
                    }
                }
            }
        }
        return new Pair<>(invert(map), array);
    }
    private LinkedHashMap<Integer, Symbol> invert(LinkedHashMap<Symbol, Integer> map) {
        LinkedHashMap<Integer, Symbol> inv = new LinkedHashMap<>();
        for (Symbol key : map.keySet()) {
            inv.put(map.get(key), key);
        }
        return inv;
    }
    public void printAllShortestPath() {
        Pair<LinkedHashMap<Integer, Symbol>, int[][]> pair;
        pair = this.floydWarshallWithKeys();
        for (int i = 0; i < pair.getValue().length; i++) {
            for (int j = 0; j < pair.getValue()[0].length; j++) {
                System.out.println(pair.getKey().get(i) + " -> " + pair.getKey().get(j) + " = " + pair.getValue()[i][j]);
            }
        }
    }
    public void printShortestPath(Symbol key) {
        LinkedHashMap<Symbol, Pair<Integer, Symbol>> map = this.bellmanFord(key);
        for (Symbol element : map.keySet()) {
            System.out.println(key + " -> " + element + " = " + map.get(element));
        }
    }
}
