package Cookies.Graph;/* Created by oguzkeremyildiz on 27.04.2020 */

import Cookies.Set.LinkedDisjointSet;
import Cookies.Tuple.Pair;
import Cookies.Tuple.Triplet;

import java.util.*;

/**
 * @author oguzkeremyildiz
 * @version 1.0.6
 */

public class LinkedWeightedGraph<Symbol, Length> {
    private LinkedHashMap<Symbol, LinkedList<Pair<Symbol, Length>>> edgeList;
    private LinkedHashSet<Symbol> vertexList;
    private LengthInterface<Length> lengthInterface;

    public LinkedWeightedGraph(LengthInterface<Length> lengthInterface) {
        edgeList = new LinkedHashMap<>();
        vertexList = new LinkedHashSet<>();
        this.lengthInterface = lengthInterface;
    }

    public LinkedWeightedGraph(LinkedHashSet<Symbol> vertexList, LengthInterface<Length> lengthInterface) {
        edgeList = new LinkedHashMap<>();
        this.vertexList = vertexList;
        this.lengthInterface = lengthInterface;
    }

    public void clear() {
        edgeList.clear();
        vertexList.clear();
    }

    public LinkedList<Pair<Symbol, Length>> getFirst() {
        Map.Entry<Symbol, LinkedList<Pair<Symbol, Length>>> entry = edgeList.entrySet().iterator().next();
        Symbol key = entry.getKey();
        return edgeList.get(key);
    }

    public boolean isEmpty() {
        return edgeList.isEmpty();
    }

    public boolean containsKey(Symbol element) {
        return edgeList.containsKey(element);
    }

    public boolean containsValue(LinkedList<Pair<Symbol, Length>> list) {
        return edgeList.containsValue(list);
    }

    public void addDirectedEdge(Symbol from, Symbol to, Length length) {
        vertexList.add(from);
        vertexList.add(to);
        if (!edgeList.containsKey(from)){
            edgeList.put(from, new LinkedList<>());
            edgeList.get(from).addFirst(new Pair<>(to, length));
        } else {
            edgeList.get(from).add(new Pair<>(to, length));
        }
    }

    public void addUndirectedEdge(Symbol from, Symbol to, Length length) {
        addDirectedEdge(from, to, length);
        addDirectedEdge(to, from, length);
    }

    public void addUndirectedEdge(Symbol from, Symbol to, Length lengthFrom, Length lengthTo) {
        addDirectedEdge(from, to, lengthTo);
        addDirectedEdge(to, from, lengthFrom);
    }

    public Set<Symbol> getKeySet() {
        return edgeList.keySet();
    }

    public int size() {
        return edgeList.size();
    }

    public void put(Symbol index, LinkedList<Pair<Symbol, Length>> list) {
        vertexList.add(index);
        edgeList.put(index, list);
        if (list.size() > 0) {
            for (Pair<Symbol, Length> element : list) {
                vertexList.add(element.getKey());
            }
        }
    }

    public LinkedList<Pair<Symbol, Length>> get(Symbol index) {
        return edgeList.get(index);
    }

    public Pair<Symbol, Length> get(Symbol element, int index) {
        return edgeList.get(element).get(index);
    }

    public LinkedHashMap<Symbol, Pair<Length, Symbol>> bellmanFord(Symbol edge) {
        LinkedHashMap<Symbol, Pair<Length, Symbol>> map = new LinkedHashMap<>();
        map.put(edge, new Pair<>(lengthInterface.min(), edge));
        for (Symbol element : vertexList) {
            if (!element.equals(edge)) {
                map.put(element, new Pair<>(lengthInterface.max(), null));
            }
        }
        for (int t = 0; t < vertexList.size() - 1; t++) {
            for (Symbol key : map.keySet()) {
                if (this.containsKey(key)) {
                    for (int i = 0; i < this.get(key).size(); i++) {
                        Pair<Symbol, Length> element = this.get(key).get(i);
                        if (!map.get(key).getKey().equals(lengthInterface.max())) {
                            if (lengthInterface.compare(map.get(element.getKey()).getKey(), lengthInterface.add(element.getValue(), map.get(key).getKey())) > 0) {
                                map.put(element.getKey(), new Pair<>(lengthInterface.add(element.getValue(), map.get(key).getKey()), key));
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    public LinkedList<LinkedList<Length>> floydWarshall() {
        LinkedList<LinkedList<Length>> array = new LinkedList<>();
        for (int j = 0; j < vertexList.size(); j++) {
            array.add(new LinkedList<>());
            for (int i = 0; i < vertexList.size(); i++) {
                array.getLast().add(lengthInterface.min());
            }
        }
        Length current;
        LinkedHashMap<Symbol, Integer> map = new LinkedHashMap<>();
        int i = -1;
        for (Symbol key : vertexList) {
            i++;
            map.put(key, i);
        }
        for (int j = 0; j < vertexList.size(); j++) {
            for (int k = 0; k < vertexList.size(); k++) {
                if (j != k) {
                    array.get(j).set(k, lengthInterface.max());
                }
            }
        }
        for (Symbol key : this.getKeySet()) {
            for (int k = 0; k < this.get(key).size(); k++) {
                array.get(map.get(key)).set(map.get(this.get(key).get(k).getKey()), this.get(key).get(k).getValue());
            }
        }
        for (int j = 0; j < vertexList.size(); j++) {
            for (int k = 0; k < vertexList.size(); k++) {
                for (int l = 0; l < vertexList.size(); l++) {
                    if (lengthInterface.compare(array.get(k).get(j), lengthInterface.max()) != 0 && lengthInterface.compare(array.get(j).get(l), lengthInterface.max()) != 0) {
                        current = lengthInterface.add(array.get(k).get(j), array.get(j).get(l));
                        if (lengthInterface.compare(array.get(k).get(l), current) > 0) {
                            array.get(k).set(l, current);
                        }
                    }
                }
            }
        }
        return array;
    }

    public Pair<LinkedHashMap<Integer, Symbol>, LinkedList<LinkedList<Length>>> floydWarshallWithKeys() {
        LinkedList<LinkedList<Length>> array = new LinkedList<>();
        for (int j = 0; j < vertexList.size(); j++) {
            array.add(new LinkedList<>());
            for (int i = 0; i < vertexList.size(); i++) {
                array.getLast().add(lengthInterface.min());
            }
        }
        Length current;
        LinkedHashMap<Symbol, Integer> map = new LinkedHashMap<>();
        int i = -1;
        for (Symbol key : vertexList) {
            i++;
            map.put(key, i);
        }
        for (int j = 0; j < vertexList.size(); j++) {
            for (int k = 0; k < vertexList.size(); k++) {
                if (j != k) {
                    array.get(j).set(k, lengthInterface.max());
                }
            }
        }
        for (Symbol key : this.getKeySet()) {
            for (int k = 0; k < this.get(key).size(); k++) {
                array.get(map.get(key)).set(map.get(this.get(key).get(k).getKey()), this.get(key).get(k).getValue());
            }
        }
        for (int j = 0; j < vertexList.size(); j++) {
            for (int k = 0; k < vertexList.size(); k++) {
                for (int l = 0; l < vertexList.size(); l++) {
                    if (lengthInterface.compare(array.get(k).get(j), lengthInterface.max()) != 0 && lengthInterface.compare(array.get(j).get(l), lengthInterface.max()) != 0) {
                        current = lengthInterface.add(array.get(k).get(j), array.get(j).get(l));
                        if (lengthInterface.compare(array.get(k).get(l), current) > 0) {
                            array.get(k).set(l, current);
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
        Pair<LinkedHashMap<Integer, Symbol>, LinkedList<LinkedList<Length>>> pair;
        pair = this.floydWarshallWithKeys();
        for (int i = 0; i < pair.getValue().size(); i++) {
            for (int j = 0; j < pair.getValue().get(i).size(); j++) {
                System.out.println(pair.getKey().get(i) + " -> " + pair.getKey().get(j) + " = " + pair.getValue().get(i).get(j));
            }
        }
    }

    public void printShortestPath(Symbol key) {
        LinkedHashMap<Symbol, Pair<Length, Symbol>> map = this.bellmanFord(key);
        for (Symbol element : map.keySet()) {
            System.out.println(key + " -> " + element + " = " + map.get(element));
        }
    }

    public Length kruskal() {
        Length total = lengthInterface.min();
        LinkedList<Triplet<Symbol, Symbol, Length>> list = new LinkedList<>();
        Symbol[] nodes = (Symbol[]) new Object[vertexList.size()];
        int j = -1;
        for (Symbol element : vertexList) {
            j++;
            nodes[j] = element;
        }
        LinkedDisjointSet<Symbol> set = new LinkedDisjointSet<>(nodes);
        for (Symbol key : edgeList.keySet()) {
            for (int i = 0; i < edgeList.get(key).size(); i++) {
                if (!list.contains(new Triplet<>(edgeList.get(key).get(i).getKey(), key, edgeList.get(key).get(i).getValue()))) {
                    list.add(new Triplet<>(key, edgeList.get(key).get(i).getKey(), edgeList.get(key).get(i).getValue()));
                }
            }
        }
        sort(list);
        for (Triplet<Symbol, Symbol, Length> tripletElement : list) {
            if (set.findSet(tripletElement.getA()) != set.findSet(tripletElement.getB())) {
                set.union(tripletElement.getA(), tripletElement.getB());
                total = lengthInterface.add(total, tripletElement.getC());
            }
        }
        return total;
    }

    private void sort(LinkedList<Triplet<Symbol, Symbol, Length>> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (lengthInterface.compare(list.get(i).getC(), list.get(j).getC()) < 0) {
                    Triplet<Symbol, Symbol, Length> tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }
    }
}
