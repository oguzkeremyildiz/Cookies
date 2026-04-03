package Cookies.Graph;/* Created by oguzkeremyildiz on 11.04.2020 */

import Cookies.Set.DisjointSet;
import Cookies.Tuple.Pair;
import Cookies.Tuple.Triplet;

import java.util.*;

/**
 * @author oguzkeremyildiz
 * @version 1.1.3
 */

public class WeightedGraph<Symbol, Length> {
    private HashMap<Symbol, LinkedList<Pair<Symbol, Edge<Length>>>> edgeList;
    private HashSet<Symbol> vertexList;
    private LengthInterface<Length> lengthInterface;

    public WeightedGraph(LengthInterface<Length> lengthInterface) {
        edgeList = new HashMap<>();
        vertexList = new HashSet<>();
        this.lengthInterface = lengthInterface;
    }

    public WeightedGraph(HashSet<Symbol> vertexList, LengthInterface<Length> lengthInterface) {
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

    public void addUndirectedEdge(Symbol from, Symbol to, Edge<Length> length) {
        addDirectedEdge(from, to, length);
        addDirectedEdge(to, from, length);
    }

    public void addUndirectedEdge(Symbol from, Symbol to, Length length) {
        addDirectedEdge(from, to, length);
        addDirectedEdge(to, from, length);
    }

    public void addUndirectedEdge(Symbol from, Symbol to, Edge<Length> lengthFrom, Edge<Length> lengthTo) {
        addDirectedEdge(from, to, lengthTo);
        addDirectedEdge(to, from, lengthFrom);
    }

    public void addUndirectedEdge(Symbol from, Symbol to, Length lengthFrom, Length lengthTo) {
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

    public LinkedList<Pair<Symbol, Edge<Length>>> get(Symbol element) {
        return edgeList.get(element);
    }

    public Pair<Symbol, Edge<Length>> get(Symbol element, int index) {
        return edgeList.get(element).get(index);
    }

    public HashMap<Symbol, Pair<Length, Symbol>> bellmanFord(Symbol edge) {
        HashMap<Symbol, Pair<Length, Symbol>> map = new HashMap<>();
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

    public ArrayList<ArrayList<Length>> floydWarshall() {
        ArrayList<ArrayList<Length>> array = new ArrayList<>();
        for (int j = 0; j < vertexList.size(); j++) {
            array.add(new ArrayList<>());
            for (int i = 0; i < vertexList.size(); i++) {
                array.get(array.size() - 1).add(lengthInterface.min());
            }
        }
        Length current;
        HashMap<Symbol, Integer> map = new HashMap<>();
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

    public Pair<HashMap<Integer, Symbol>, ArrayList<ArrayList<Length>>> floydWarshallWithKeys() {
        ArrayList<ArrayList<Length>> array = new ArrayList<>();
        for (int j = 0; j < vertexList.size(); j++) {
            array.add(new ArrayList<>());
            for (int i = 0; i < vertexList.size(); i++) {
                array.get(array.size() - 1).add(lengthInterface.min());
            }
        }
        Length current;
        HashMap<Symbol, Integer> map = new HashMap<>();
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

    private HashMap<Integer, Symbol> invert(HashMap<Symbol, Integer> map) {
        HashMap<Integer, Symbol> inv = new HashMap<>();
        for (Symbol key : map.keySet()) {
            inv.put(map.get(key), key);
        }
        return inv;
    }

    public void printAllShortestPath() {
        Pair<HashMap<Integer, Symbol>, ArrayList<ArrayList<Length>>> pair;
        pair = this.floydWarshallWithKeys();
        for (int i = 0; i < pair.getValue().size(); i++) {
            for (int j = 0; j < pair.getValue().get(i).size(); j++) {
                System.out.println(pair.getKey().get(i) + " -> " + pair.getKey().get(j) + " = " + pair.getValue().get(i).get(j));
            }
        }
    }

    public void printShortestPathBellmanFord(Symbol key) {
        HashMap<Symbol, Pair<Length, Symbol>> map = this.bellmanFord(key);
        for (Symbol element : map.keySet()) {
            System.out.println(key + " -> " + element + " = " + map.get(element));
        }
    }

    public Length prims() {
        if (vertexList.isEmpty()) return lengthInterface.min();
        Length total = lengthInterface.min();
        HashSet<Symbol> visited = new HashSet<>();
        PriorityQueue<Triplet<Symbol, Symbol, Length>> pq = new PriorityQueue<>((a, b) -> lengthInterface.compare(a.getC(), b.getC()));
        Symbol start = vertexList.iterator().next();
        visited.add(start);
        if (edgeList.containsKey(start)) {
            for (Pair<Symbol, Edge<Length>> edge : edgeList.get(start)) {
                pq.add(new Triplet<>(start, edge.getKey(), edge.getValue().getLength()));
            }
        }
        while (!pq.isEmpty() && visited.size() < vertexList.size()) {
            Triplet<Symbol, Symbol, Length> edge = pq.poll();
            if (!visited.contains(edge.getB())) {
                visited.add(edge.getB());
                total = lengthInterface.add(total, edge.getC());
                if (edgeList.containsKey(edge.getB())) {
                    for (Pair<Symbol, Edge<Length>> nextEdge : edgeList.get(edge.getB())) {
                        if (!visited.contains(nextEdge.getKey())) {
                            pq.add(new Triplet<>(edge.getB(), nextEdge.getKey(), nextEdge.getValue().getLength()));
                        }
                    }
                }
            }
        }
        return total;
    }

    public Length kruskal() {
        Length total = lengthInterface.min();
        List<Triplet<Symbol, Symbol, Length>> allEdges = new ArrayList<>();
        Symbol[] nodes = (Symbol[]) java.lang.reflect.Array.newInstance(vertexList.iterator().next().getClass(), vertexList.size());
        int idx = 0;
        for (Symbol s : vertexList) nodes[idx++] = s;
        DisjointSet<Symbol> set = new DisjointSet<>(nodes);
        for (Symbol u : edgeList.keySet()) {
            for (Pair<Symbol, Edge<Length>> pair : edgeList.get(u)) {
                allEdges.add(new Triplet<>(u, pair.getKey(), pair.getValue().getLength()));
            }
        }
        allEdges.sort((a, b) -> lengthInterface.compare(a.getC(), b.getC()));
        for (Triplet<Symbol, Symbol, Length> edge : allEdges) {
            if (set.findSet(edge.getA()) != set.findSet(edge.getB())) {
                set.union(edge.getA(), edge.getB());
                total = lengthInterface.add(total, edge.getC());
            }
        }
        return total;
    }

    public HashMap<Symbol, Pair<Length, Symbol>> dijkstra(Symbol source) {
        HashMap<Symbol, Pair<Length, Symbol>> results = new HashMap<>();
        HashSet<Symbol> visited = new HashSet<>();
        PriorityQueue<Pair<Symbol, Length>> pq = new PriorityQueue<>((a, b) -> lengthInterface.compare(a.getValue(), b.getValue()));
        for (Symbol v : vertexList) {
            results.put(v, new Pair<>(lengthInterface.max(), null));
        }
        results.put(source, new Pair<>(lengthInterface.min(), source));
        pq.add(new Pair<>(source, lengthInterface.min()));
        while (!pq.isEmpty()) {
            Pair<Symbol, Length> current = pq.poll();
            Symbol u = current.getKey();
            Length distToU = current.getValue();
            if (!visited.contains(u)) {
                visited.add(u);
                if (edgeList.containsKey(u)) {
                    for (Pair<Symbol, Edge<Length>> edge : edgeList.get(u)) {
                        Symbol v = edge.getKey();
                        Length weight = edge.getValue().getLength();
                        Length newDist = lengthInterface.add(distToU, weight);
                        if (lengthInterface.compare(newDist, results.get(v).getKey()) < 0) {
                            results.put(v, new Pair<>(newDist, u));
                            pq.add(new Pair<>(v, newDist));
                        }
                    }
                }
            }
        }
        return results;
    }

    public void printShortestPathDijkstra(Symbol key) {
        HashMap<Symbol, Pair<Length, Symbol>> map = this.dijkstra(key);
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
        for (int i = 0; i < edgeList.get(edge.getKey()).size(); i++) {
            Pair<Symbol, Edge<Length>> element = edgeList.get(edge.getKey()).get(i);
            if (element.getKey().equals(edge.getValue().getKey())) {
                edgeList.get(edge.getKey()).remove(i);
                break;
            }
        }
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

    public LinkedList<WeightedGraph<Symbol, Length>> connectedComponents() {
        LinkedList<WeightedGraph<Symbol, Length>> graphs = new LinkedList<>();
        int i;
        HashMap<Symbol, Boolean> visited = new HashMap<>();
        for (Symbol vertex: vertexList) {
            visited.put(vertex, false);
        }
        for (Symbol vertex: vertexList) {
            if (!visited.get(vertex)) {
                visited.put(vertex, true);
                WeightedGraph<Symbol, Length> connectedComponent = new WeightedGraph<>(lengthInterface);
                depthFirstSearch(connectedComponent, vertex, visited);
                graphs.add(connectedComponent);
            }
        }
        return graphs;
    }

    private void depthFirstSearch(WeightedGraph<Symbol, Length> connectedComponent, Symbol i, HashMap<Symbol, Boolean> visited) {
        if (containsKey(i)) {
            connectedComponent.put(i, get(i));
            for (Pair<Symbol, Edge<Length>> toNode : get(i)) {
                if (!visited.get(toNode.getKey())){
                    visited.put(toNode.getKey(), true);
                    depthFirstSearch(connectedComponent, toNode.getKey(), visited);
                }
            }
        }
    }
}
