package Graph;/* Created by oguzkeremyildiz on 11.04.2020 */

import java.util.*;

public class Graph<Symbol> {
    private HashSet<Symbol> vertexList;
    private HashMap<Symbol, LinkedList<Symbol>> edgeList;

    public Graph(){
        edgeList = new HashMap<Symbol, LinkedList<Symbol>>();
        vertexList = new HashSet<Symbol>();
    }

    public Graph(HashSet<Symbol> vertexList){
        edgeList = new HashMap<Symbol, LinkedList<Symbol>>();
        this.vertexList = vertexList;
    }

    public boolean containsKey(Symbol element) {
        return edgeList.containsKey(element);
    }

    public boolean containsValue(LinkedList<Symbol> list) {
        return edgeList.containsValue(list);
    }

    public boolean isEmpty() {
        return edgeList.isEmpty();
    }

    public int size() {
        return edgeList.size();
    }

    public void put(Symbol index, LinkedList<Symbol> list) {
        edgeList.put(index, list);
    }

    public LinkedList<Symbol> get(Symbol index) {
        return edgeList.get(index);
    }

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

    public void addUndirectedEdge(Symbol from, Symbol to) {
        addDirectedEdge(from, to);
        addDirectedEdge(to, from);
    }

    public void clear() {
        edgeList.clear();
        vertexList.clear();
    }

    public Set<Symbol> getKeySet() {
        return edgeList.keySet();
    }

    public LinkedList<Graph<Symbol>> connectedComponents() {
        LinkedList<Graph<Symbol>> graphs = new LinkedList<Graph<Symbol>>();
        int i;
        HashMap<Symbol, Boolean> visited = new HashMap<Symbol, Boolean>();
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
