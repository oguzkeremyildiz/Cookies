package Cookies.Graph;/* Created by oguzkeremyildiz on 27.04.2020 */

import Cookies.Set.LinkedDisjointSet;
import Cookies.Tuple.Pair;
import Cookies.Tuple.Triplet;

import java.util.*;

/**
 * @author oguzkeremyildiz
 * @version 1.1.1
 */

public class LinkedWeightedGraph<Symbol, Length> {
    private LinkedHashMap<Symbol, LinkedList<Pair<Symbol, Edge<Length>>>> edgeList;
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

    public LinkedList<Pair<Symbol, Edge<Length>>> getFirst() {
        Map.Entry<Symbol, LinkedList<Pair<Symbol, Edge<Length>>>> entry = edgeList.entrySet().iterator().next();
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

    public void addDirectedEdge(Symbol from, Symbol to, Length length) {
        vertexList.add(from);
        vertexList.add(to);
        if (!edgeList.containsKey(from)){
            edgeList.put(from, new LinkedList<>());
            edgeList.get(from).addFirst(new Pair<>(to, new Edge<>(length)));
        } else {
            edgeList.get(from).add(new Pair<>(to, new Edge<>(length)));
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
                        Pair<Symbol, Edge<Length>> element = this.get(key).get(i);
                        if (!map.get(key).getKey().equals(lengthInterface.max())) {
                            if (lengthInterface.compare(map.get(element.getKey()).getKey(), lengthInterface.add(element.getValue().getLength(), map.get(key).getKey())) > 0) {
                                map.put(element.getKey(), new Pair<>(lengthInterface.add(element.getValue().getLength(), map.get(key).getKey()), key));
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
                array.get(map.get(key)).set(map.get(this.get(key).get(k).getKey()), this.get(key).get(k).getValue().getLength());
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
                array.get(map.get(key)).set(map.get(this.get(key).get(k).getKey()), this.get(key).get(k).getValue().getLength());
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

    public Length prims() {
        Length total = lengthInterface.min();
        LinkedHashSet<Symbol> elements = new LinkedHashSet<>();
        for (Symbol element : vertexList) {
            elements.add(element);
            break;
        }
        while (!elements.containsAll(vertexList)) {
            Symbol edge = null;
            Length minimum = lengthInterface.max();
            for (Symbol element : elements) {
                for (int i = 0; i < get(element).size(); i++) {
                    Pair<Symbol, Edge<Length>> pair = get(element, i);
                    if (!elements.contains(pair.getKey()) && lengthInterface.compare(pair.getValue().getLength(), minimum) < 0) {
                        minimum = pair.getValue().getLength();
                        edge = pair.getKey();
                    }
                }
            }
            if (edge != null) {
                elements.add(edge);
                total = lengthInterface.add(total, minimum);
            }
        }
        return total;
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
                if (!list.contains(new Triplet<>(edgeList.get(key).get(i).getKey(), key, edgeList.get(key).get(i).getValue().getLength()))) {
                    list.add(new Triplet<>(key, edgeList.get(key).get(i).getKey(), edgeList.get(key).get(i).getValue().getLength()));
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

    public LinkedHashMap<Symbol, Pair<Length, Symbol>> dijkstra(Symbol edge) {
        LinkedHashSet<Symbol> visited = new LinkedHashSet<>();
        LinkedHashMap<Symbol, Pair<Length, Symbol>> map = new LinkedHashMap<>();
        visited.add(edge);
        for (Symbol element : vertexList) {
            if (edge.equals(element)) {
                map.put(element, new Pair<>(lengthInterface.min(), edge));
            } else if (containsElement(edge, element).getKey()) {
                map.put(element, new Pair<>(get(edge, containsElement(edge, element).getValue()).getValue().getLength(), edge));
            } else {
                map.put(element, new Pair<>(lengthInterface.max(), null));
            }
        }
        for (int i = 0; i < vertexList.size() - 1; i++) {
            Symbol key = findMinimum(visited, map);
            visited.add(key);
            if (containsKey(key)) {
                for (int j = 0; j < get(key).size(); j++) {
                    if (lengthInterface.compare(lengthInterface.add(map.get(key).getKey(), get(key, j).getValue().getLength()), map.get(get(key, j).getKey()).getKey()) < 0) {
                        map.put(get(key, j).getKey(), new Pair<>(lengthInterface.add(map.get(key).getKey(), get(key, j).getValue().getLength()), key));
                    }
                }
            }
        }
        return map;
    }

    private Symbol findMinimum(LinkedHashSet<Symbol> visited, LinkedHashMap<Symbol, Pair<Length, Symbol>> map) {
        Symbol element = null;
        Length length = lengthInterface.max();
        for (Symbol key : map.keySet()) {
            if (!visited.contains(key)) {
                if (lengthInterface.compare(length, map.get(key).getKey()) > 0) {
                    length = map.get(key).getKey();
                    element = key;
                }
            }
        }
        return element;
    }

    private Pair<Boolean, Integer> containsElement(Symbol edge, Symbol element) {
        for (int i = 0; i < get(edge).size(); i++) {
            if (get(edge, i).getKey().equals(element)) {
                return new Pair<>(true, i);
            }
        }
        return new Pair<>(false, -1);
    }

    public void printShortestPathDijkstra(Symbol key) {
        LinkedHashMap<Symbol, Pair<Length, Symbol>> map = this.dijkstra(key);
        for (Symbol element : map.keySet()) {
            System.out.println(key + " -> " + element + " = " + map.get(element));
        }
    }

    public Length fordFulkerson(Symbol source, Symbol sink) {
        Length total = lengthInterface.min();
        LinkedList<Pair<Symbol, Pair<Symbol, Edge<Length>>>> edges = addEdges();
        while (true) {
            LinkedList<Symbol> nodes = new LinkedList<>();
            nodes.add(source);
            Length min = depthFirstSearch(nodes, source, lengthInterface.max(), sink);
            if (!nodes.contains(sink)) {
                break;
            }
            setResiduals(nodes, min);
            total = lengthInterface.add(total, min);
        }
        removeEdges(edges);
        return total;
    }

    private LinkedList<Pair<Symbol, Pair<Symbol, Edge<Length>>>> addEdges() {
        LinkedList<Pair<Symbol, Pair<Symbol, Edge<Length>>>> list = new LinkedList<>();
        for (Symbol key : edgeList.keySet()) {
            for (int i = 0; i < edgeList.get(key).size(); i++) {
                if (!containsTo(edgeList.get(key).get(i).getKey(), key)) {
                    ResidualEdge<Length> edge = new ResidualEdge<>(edgeList.get(key).get(i).getValue().getLength(), lengthInterface.min(), lengthInterface);
                    list.add(new Pair<>(edgeList.get(key).get(i).getKey(), new Pair<>(key, edge)));
                }
            }
        }
        for (Pair<Symbol, Pair<Symbol, Edge<Length>>> symbolPairPair : list) {
            addDirectedEdge(symbolPairPair.getKey(), symbolPairPair.getValue().getKey(), symbolPairPair.getValue().getValue());
        }
        return list;
    }

    private boolean containsTo(Symbol from, Symbol to) {
        if (edgeList.containsKey(from)) {
            for (int i = 0; i < edgeList.get(from).size(); i++) {
                if (edgeList.get(from).get(i).getKey().equals(to)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void removeEdges(LinkedList<Pair<Symbol, Pair<Symbol, Edge<Length>>>> edges) {
        for (Pair<Symbol, Pair<Symbol, Edge<Length>>> edge : edges) {
            removeEdge(edge);
            if (edgeList.get(edge.getKey()).isEmpty()) {
                edgeList.remove(edge.getKey());
            }
        }
    }

    private void removeEdge(Pair<Symbol, Pair<Symbol, Edge<Length>>> edge) {
        edgeList.get(edge.getKey()).remove(edge.getValue());
    }

    private void setResiduals(LinkedList<Symbol> nodes, Length min) {
        for (int i = 0; i < nodes.size() - 1; i++) {
            if (containsKey(nodes.get(i))) {
                for (int j = 0; j < get(nodes.get(i)).size(); j++) {
                    if (get(nodes.get(i), j).getKey().equals(nodes.get(i + 1))) {
                        ((ResidualEdge<Length>) get(nodes.get(i), j).getValue()).setFlow(lengthInterface.add(((ResidualEdge<Length>) get(nodes.get(i), j).getValue()).getFlow(), min));
                        if (containsKey(nodes.get(i + 1))) {
                            for (int k = 0; k < get(nodes.get(i + 1)).size(); k++) {
                                if (get(nodes.get(i + 1), k).getKey().equals(nodes.get(i))) {
                                    ((ResidualEdge<Length>) get(nodes.get(i + 1), k).getValue()).setFlow(lengthInterface.remove(get(nodes.get(i + 1), k).getValue().getLength(), ((ResidualEdge<Length>) get(nodes.get(i), j).getValue()).getFlow()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private Length depthFirstSearch(LinkedList<Symbol> nodes, Symbol current, Length min, Symbol sink) {
        for (int i = 0; i < get(current).size(); i++) {
            if (nodes.contains(sink)) {
                return min;
            }
            Symbol node = get(current, i).getKey();
            if (!nodes.contains(node) && lengthInterface.compare(((ResidualEdge<Length>) get(current, i).getValue()).getResidual(), lengthInterface.min()) > 0) {
                nodes.add(node);
                if (nodes.contains(sink)) {
                    return lengthInterface.min(min, ((ResidualEdge<Length>) get(current, i).getValue()).getResidual());
                }
                min = lengthInterface.min(min, depthFirstSearch(nodes, node, lengthInterface.min(min, ((ResidualEdge<Length>) get(current, i).getValue()).getResidual()), sink));
                if (!nodes.contains(sink)) {
                    nodes.removeLast();
                }
            }
        }
        return min;
    }

    public LinkedList<LinkedWeightedGraph<Symbol, Length>> connectedComponents() {
        LinkedList<LinkedWeightedGraph<Symbol, Length>> graphs = new LinkedList<>();
        int i;
        LinkedHashMap<Symbol, Boolean> visited = new LinkedHashMap<>();
        for (Symbol vertex: vertexList) {
            visited.put(vertex, false);
        }
        for (Symbol vertex: vertexList) {
            if (!visited.get(vertex)) {
                visited.put(vertex, true);
                LinkedWeightedGraph<Symbol, Length> connectedComponent = new LinkedWeightedGraph<>(lengthInterface);
                depthFirstSearch(connectedComponent, vertex, visited);
                graphs.add(connectedComponent);
            }
        }
        return graphs;
    }

    private void depthFirstSearch(LinkedWeightedGraph<Symbol, Length> connectedComponent, Symbol i, LinkedHashMap<Symbol, Boolean> visited) {
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
