package Graph;/* Created by oguzkeremyildiz on 11.04.2020 */

import Tuples.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class WeightedGraph<Symbol> {
    private HashMap<Symbol, LinkedList<Pair<Symbol, Integer>>> edgeList;
    private HashSet<Symbol> vertexList;

    public WeightedGraph(){
        edgeList = new HashMap<Symbol, LinkedList<Pair<Symbol, Integer>>>();
        vertexList = new HashSet<Symbol>();
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
            edgeList.put(from, new LinkedList<Pair<Symbol, Integer>>());
            edgeList.get(from).addFirst(new Pair<Symbol, Integer>(to, length));
        } else {
            edgeList.get(from).add(new Pair<Symbol, Integer>(to, length));
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

    public LinkedList<Pair<Symbol, Integer>> get(Symbol index) {
        return edgeList.get(index);
    }
}
