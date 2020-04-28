package Graph;/* Created by oguzkeremyildiz on 28.04.2020 */

import java.util.*;

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class LinkedGraph<Symbol> {
    private LinkedHashSet<Symbol> vertexList;
    private LinkedHashMap<Symbol, LinkedList<Symbol>> edgeList;

    /**
     * A constructor of {@link Graph} class which Initialized new {@link java.util.HashMap} and {@link java.util.HashSet}.
     */

    public LinkedGraph(){
        edgeList = new LinkedHashMap<>();
        vertexList = new LinkedHashSet<>();
    }

    public LinkedGraph(LinkedHashSet<Symbol> vertexList){
        edgeList = new LinkedHashMap<>();
        this.vertexList = vertexList;
    }

    /**
     * A containsKey method which is the same as {@link java.util.HashMap}'s.
     * @param element Symbol input.
     * @return is control {@link Graph} for contains key.
     */

    public boolean containsKey(Symbol element) {
        return edgeList.containsKey(element);
    }

    /**
     * A containsValue method which is the same as {@link java.util.HashMap}'s.
     * @param list {@link java.util.LinkedList} input.
     * @return is control {@link Graph} for contains value.
     */

    public boolean containsValue(LinkedList<Symbol> list) {
        return edgeList.containsValue(list);
    }

    /**
     * A isEmpty method which is the same as {@link java.util.HashMap}'s.
     * @return control are there any element in {@link Graph}.
     */

    public boolean isEmpty() {
        return edgeList.isEmpty();
    }

    /**
     * A size method is returns {@link Graph}'s size.
     * @return edgeList's length.
     */

    public int size() {
        return edgeList.size();
    }

    /**
     * A put method adds the list to the index.
     * @param index Symbol input.
     * @param list LinkedList input.
     */

    public void put(Symbol index, LinkedList<Symbol> list) {
        vertexList.add(index);
        edgeList.put(index, list);
        if (list.size() > 0) {
            for (Symbol element : list) {
                vertexList.add(element);
            }
        }
    }

    /**
     * A get method returns the list brought by index,
     * @param index Symbol input.
     * @return {@link java.util.LinkedList} of Symbol.
     */

    public LinkedList<Symbol> get(Symbol index) {
        return edgeList.get(index);
    }

    /**
     * A addDirectedEdge add an element to from.
     * @param from Symbol input.
     * @param to Symbol input.
     */

    public void addDirectedEdge(Symbol from, Symbol to) {
        vertexList.add(from);
        vertexList.add(to);
        if (!edgeList.containsKey(from)){
            edgeList.put(from, new LinkedList<Symbol>());
            edgeList.get(from).addFirst(to);
        } else {
            edgeList.get(from).add(to);
        }
    }

    /**
     * A addUndirectedEdge adds on both sides.
     * @param from Symbol input.
     * @param to Symbol input.
     */

    public void addUndirectedEdge(Symbol from, Symbol to) {
        addDirectedEdge(from, to);
        addDirectedEdge(to, from);
    }

    /**
     * A clear method clears all {@link Graph}.
     */

    public void clear() {
        edgeList.clear();
        vertexList.clear();
    }

    /**
     * A replace method puts another {@link java.util.LinkedList} in key.
     * @param key Symbol input.
     * @param value {@link java.util.LinkedList} input.
     */

    public void replace(Symbol key, LinkedList<Symbol> value) {
        edgeList.replace(key, value);
        if (value.size() > 0) {
            for (Symbol element : value) {
                vertexList.add(element);
            }
        }
    }

    /**
     * A getKeySet get all {@link Graph}'s vertex.
     * @return edgeList's keySet.
     */

    public Set<Symbol> getKeySet() {
        return edgeList.keySet();
    }

    /**
     * A getFirst get first added list.
     * @return edgeList's first key.
     */

    public LinkedList<Symbol> getFirst() {
        Map.Entry<Symbol, LinkedList<Symbol>> entry = edgeList.entrySet().iterator().next();
        Symbol key = entry.getKey();
        return edgeList.get(key);
    }

    /**
     * A connectedComponents method travels all {@link Graph}.
     * @return A {@link java.util.LinkedList} of {@link Graph}.
     */

    public LinkedList<Graph<Symbol>> connectedComponents() {
        LinkedList<Graph<Symbol>> graphs = new LinkedList<Graph<Symbol>>();
        int i;
        HashMap<Symbol, Boolean> visited = new HashMap<>();
        for (Symbol vertex: vertexList) {
            visited.put(vertex, false);
        }
        for (Symbol vertex: vertexList) {
            if (!visited.get(vertex)) {
                visited.put(vertex, true);
                Graph<Symbol> connectedComponent = new Graph<Symbol>();
                depthFirstSearch(connectedComponent, vertex, visited);
                graphs.add(connectedComponent);
            }
        }
        return graphs;
    }

    /**
     * A depthFirstSearch method is separates the {@link Graph}s without connection.
     * @param connectedComponent {@link Graph} input.
     * @param i Symbol input.
     * @param visited {@link java.util.HashSet} input.
     */

    private void depthFirstSearch(Graph<Symbol> connectedComponent, Symbol i, HashMap<Symbol, Boolean> visited) {
        connectedComponent.put(i, get(i));
        for (Symbol toNode : get(i)){
            if (!visited.get(toNode)){
                visited.put(toNode, true);
                depthFirstSearch(connectedComponent, toNode, visited);
            }
        }
    }
}
