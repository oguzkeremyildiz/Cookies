package Cookies.Graph;

import Cookies.Tuple.Pair;

import java.util.*;

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class WeightedGraphEdge<Symbol, Length> {
    private HashMap<Symbol, LinkedList<Pair<Symbol, Edge<Length>>>> edgeList;
    private HashSet<Symbol> vertexList;
    private LengthInterface<Length> lengthInterface;

    public WeightedGraphEdge(LengthInterface<Length> lengthInterface) {
        edgeList = new HashMap<>();
        vertexList = new HashSet<>();
        this.lengthInterface = lengthInterface;
    }

    public WeightedGraphEdge(HashSet<Symbol> vertexList, LengthInterface<Length> lengthInterface) {
        edgeList = new HashMap<>();
        this.vertexList = vertexList;
        this.lengthInterface = lengthInterface;
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

    public boolean containsValue(LinkedList<Pair<Symbol, Edge<Length>>> list) {
        return edgeList.containsValue(list);
    }

    public boolean contains(Symbol element) {
        return vertexList.contains(element);
    }

    public void addDirectedEdge(Symbol from, Symbol to, Edge<Length> length) {
        vertexList.add(from);
        vertexList.add(to);
        if (!edgeList.containsKey(from)){
            edgeList.put(from, new LinkedList<>());
            edgeList.get(from).addFirst(new Pair<>(to, length));
        } else {
            edgeList.get(from).add(new Pair<>(to, length));
        }
    }

    public void addUndirectedEdge(Symbol from, Symbol to, Edge<Length> length) {
        addDirectedEdge(from, to, length);
        addDirectedEdge(to, from, length);
    }

    public void addUndirectedEdge(Symbol from, Symbol to, Edge<Length> lengthFrom, Edge<Length> lengthTo) {
        addDirectedEdge(from, to, lengthTo);
        addDirectedEdge(to, from, lengthFrom);
    }

    public Set<Symbol> getKeySet() {
        return edgeList.keySet();
    }

    public HashSet<Symbol> getVertexList() {
        return vertexList;
    }

    public int size() {
        return edgeList.size();
    }

    public void put(Symbol index, LinkedList<Pair<Symbol, Edge<Length>>> list) {
        vertexList.add(index);
        edgeList.put(index, list);
        if (list.size() > 0) {
            for (Pair<Symbol, Edge<Length>> element : list) {
                vertexList.add(element.getKey());
            }
        }
    }

    public LinkedList<Pair<Symbol, Edge<Length>>> get(Symbol index) {
        return edgeList.get(index);
    }

    public Pair<Symbol, Edge<Length>> get(Symbol element, int index) {
        return edgeList.get(element).get(index);
    }

    public LinkedList<WeightedGraphEdge<Symbol, Length>> connectedComponents() {
        LinkedList<WeightedGraphEdge<Symbol, Length>> graphs = new LinkedList<>();
        int i;
        HashMap<Symbol, Boolean> visited = new HashMap<>();
        for (Symbol vertex: vertexList) {
            visited.put(vertex, false);
        }
        for (Symbol vertex: vertexList) {
            if (!visited.get(vertex)) {
                visited.put(vertex, true);
                WeightedGraphEdge<Symbol, Length> connectedComponent = new WeightedGraphEdge<>(lengthInterface);
                depthFirstSearch(connectedComponent, vertex, visited);
                graphs.add(connectedComponent);
            }
        }
        return graphs;
    }

    private void depthFirstSearch(WeightedGraphEdge<Symbol, Length> connectedComponent, Symbol i, HashMap<Symbol, Boolean> visited) {
        if (containsKey(i)) {
            connectedComponent.put(i, get(i));
            for (Pair<Symbol, Edge<Length>> toNode : get(i)){
                if (!visited.get(toNode.getKey())){
                    visited.put(toNode.getKey(), true);
                    depthFirstSearch(connectedComponent, toNode.getKey(), visited);
                }
            }
        }
    }
}