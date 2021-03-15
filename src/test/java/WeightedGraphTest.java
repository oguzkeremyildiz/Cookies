import Cookies.Graph.Edge;
import Cookies.Graph.IntegerLength;
import Cookies.Graph.ResidualEdge;
import Cookies.Graph.WeightedGraph;
import Cookies.Tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

public class WeightedGraphTest {

    private int findIndex(WeightedGraph<String, Integer> graph, LinkedList<WeightedGraph<String, Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVertexList().equals(graph.getVertexList())) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void testConnectedComponents() {
        WeightedGraph<String, Integer> graph = new WeightedGraph<>(new IntegerLength());
        graph.addDirectedEdge("1", "2", 3);
        graph.addDirectedEdge("2", "3", 5);
        graph.addDirectedEdge("2", "4", 4);
        graph.addDirectedEdge("5", "6", 2);
        graph.addDirectedEdge("9", "7", 4);
        graph.addDirectedEdge("5", "9", 2);
        graph.addDirectedEdge("11", "12", 5);
        graph.addDirectedEdge("11", "13", 8);
        LinkedList<WeightedGraph<String, Integer>> graphs = graph.connectedComponents();
        LinkedList<WeightedGraph<String, Integer>> expectation = new LinkedList<>();
        WeightedGraph<String, Integer> g1 = new WeightedGraph<>(new IntegerLength());
        g1.addDirectedEdge("1", "2", 3);
        g1.addDirectedEdge("2", "3", 5);
        g1.addDirectedEdge("2", "4", 4);
        expectation.add(g1);
        WeightedGraph<String, Integer> g2 = new WeightedGraph<>(new IntegerLength());
        g2.addDirectedEdge("5", "6", 2);
        g2.addDirectedEdge("9", "7", 4);
        g2.addDirectedEdge("5", "9", 2);
        expectation.add(g2);
        WeightedGraph<String, Integer> g3 = new WeightedGraph<>(new IntegerLength());
        g3.addDirectedEdge("11", "12", 5);
        g3.addDirectedEdge("11", "13", 8);
        expectation.add(g3);
        for (WeightedGraph<String, Integer> weightedGraph : graphs) {
            int j = findIndex(weightedGraph, expectation);
            for (String key : weightedGraph.getKeySet()) {
                Assert.assertEquals(weightedGraph.get(key), expectation.get(j).get(key));
            }
        }
    }

    @Test
    public void testSpanningTrees() {
        WeightedGraph<String, Integer> graph = new WeightedGraph<>(new IntegerLength());
        graph.addUndirectedEdge("a", "b", new Edge<>(2));
        graph.addUndirectedEdge("a", "c", new Edge<>(3));
        graph.addUndirectedEdge("b", "c", new Edge<>(4));
        graph.addUndirectedEdge("b", "d", new Edge<>(2));
        graph.addUndirectedEdge("d", "c", new Edge<>(3));
        graph.addUndirectedEdge("d", "e", new Edge<>(1));
        graph.addUndirectedEdge("c", "e", new Edge<>(2));
        graph.addUndirectedEdge("c", "g", new Edge<>(6));
        graph.addUndirectedEdge("f", "g", new Edge<>(4));
        graph.addUndirectedEdge("f", "c", new Edge<>(3));
        graph.addUndirectedEdge("f", "e", new Edge<>(3));
        Assert.assertEquals(java.util.Optional.ofNullable(graph.kruskal()), java.util.Optional.of(14));
        Assert.assertEquals(java.util.Optional.ofNullable(graph.prims()), java.util.Optional.of(14));
    }

    @Test
    public void testShortestPaths() {
        WeightedGraph<String, Integer> graph = new WeightedGraph<>(new IntegerLength());
        graph.addUndirectedEdge("a", "b", 6);
        graph.addUndirectedEdge("a", "d", 1);
        graph.addUndirectedEdge("b", "d", 2);
        graph.addUndirectedEdge("b", "c", 5);
        graph.addUndirectedEdge("b", "e", 2);
        graph.addUndirectedEdge("d", "e", 1);
        graph.addUndirectedEdge("e", "c", 5);
        HashMap<String, Pair<Integer, String>> actual = new HashMap<>();
        actual.put("a", new Pair<>(0, "a"));
        actual.put("b", new Pair<>(3, "d"));
        actual.put("c", new Pair<>(7, "e"));
        actual.put("d", new Pair<>(1, "a"));
        actual.put("e", new Pair<>(2, "d"));
        Assert.assertEquals(graph.dijkstra("a"), actual);
        Assert.assertEquals(graph.bellmanFord("a"), actual);
    }

    @Test
    public void testFordFulkerson() {
        WeightedGraph<String, Integer> graph = new WeightedGraph<>(new IntegerLength());
        graph.addDirectedEdge("s", "a", new ResidualEdge<>(10, new IntegerLength()));
        graph.addDirectedEdge("s", "c", new ResidualEdge<>(10, new IntegerLength()));
        graph.addDirectedEdge("a", "d", new ResidualEdge<>(8, new IntegerLength()));
        graph.addDirectedEdge("d", "t", new ResidualEdge<>(10, new IntegerLength()));
        graph.addDirectedEdge("a", "b", new ResidualEdge<>(4, new IntegerLength()));
        graph.addDirectedEdge("a", "c", new ResidualEdge<>(2, new IntegerLength()));
        graph.addDirectedEdge("c", "d", new ResidualEdge<>(9, new IntegerLength()));
        graph.addDirectedEdge("b", "t", new ResidualEdge<>(10, new IntegerLength()));
        graph.addDirectedEdge("d", "b", new ResidualEdge<>(6, new IntegerLength()));
        Assert.assertEquals(java.util.Optional.ofNullable(graph.fordFulkerson("s", "t")), java.util.Optional.of(19));
        graph.clear();
        graph.addDirectedEdge("0", "1", new ResidualEdge<>(16, new IntegerLength()));
        graph.addDirectedEdge("0", "2", new ResidualEdge<>(13, new IntegerLength()));
        graph.addDirectedEdge("1", "2", new ResidualEdge<>(10, new IntegerLength()));
        graph.addDirectedEdge("2", "1", new ResidualEdge<>(4, new IntegerLength()));
        graph.addDirectedEdge("2", "4", new ResidualEdge<>(14, new IntegerLength()));
        graph.addDirectedEdge("1", "3", new ResidualEdge<>(12, new IntegerLength()));
        graph.addDirectedEdge("3", "2", new ResidualEdge<>(9, new IntegerLength()));
        graph.addDirectedEdge("3", "5", new ResidualEdge<>(20, new IntegerLength()));
        graph.addDirectedEdge("4", "3", new ResidualEdge<>(7, new IntegerLength()));
        graph.addDirectedEdge("4", "5", new ResidualEdge<>(4, new IntegerLength()));
        Assert.assertEquals(java.util.Optional.ofNullable(graph.fordFulkerson("0", "5")), java.util.Optional.of(23));
        graph.clear();
        graph.addDirectedEdge("s", "a", new ResidualEdge<>(9, new IntegerLength()));
        graph.addDirectedEdge("s", "b", new ResidualEdge<>(9, new IntegerLength()));
        graph.addDirectedEdge("a", "b", new ResidualEdge<>(10, new IntegerLength()));
        graph.addDirectedEdge("a", "c", new ResidualEdge<>(8, new IntegerLength()));
        graph.addDirectedEdge("b", "c", new ResidualEdge<>(1, new IntegerLength()));
        graph.addDirectedEdge("b", "d", new ResidualEdge<>(3, new IntegerLength()));
        graph.addDirectedEdge("c", "t", new ResidualEdge<>(10, new IntegerLength()));
        graph.addDirectedEdge("d", "c", new ResidualEdge<>(8, new IntegerLength()));
        graph.addDirectedEdge("d", "t", new ResidualEdge<>(7, new IntegerLength()));
        Assert.assertEquals(java.util.Optional.ofNullable(graph.fordFulkerson("s", "t")), java.util.Optional.of(12));
        graph.clear();
        graph.addDirectedEdge("s", "0", new ResidualEdge<>(7, new IntegerLength()));
        graph.addDirectedEdge("s", "1", new ResidualEdge<>(2, new IntegerLength()));
        graph.addDirectedEdge("s", "2", new ResidualEdge<>(1, new IntegerLength()));
        graph.addDirectedEdge("0", "3", new ResidualEdge<>(2, new IntegerLength()));
        graph.addDirectedEdge("0", "4", new ResidualEdge<>(4, new IntegerLength()));
        graph.addDirectedEdge("1", "4", new ResidualEdge<>(5, new IntegerLength()));
        graph.addDirectedEdge("1", "5", new ResidualEdge<>(6, new IntegerLength()));
        graph.addDirectedEdge("2", "3", new ResidualEdge<>(4, new IntegerLength()));
        graph.addDirectedEdge("2", "7", new ResidualEdge<>(8, new IntegerLength()));
        graph.addDirectedEdge("3", "6", new ResidualEdge<>(7, new IntegerLength()));
        graph.addDirectedEdge("3", "7", new ResidualEdge<>(1, new IntegerLength()));
        graph.addDirectedEdge("4", "5", new ResidualEdge<>(8, new IntegerLength()));
        graph.addDirectedEdge("4", "6", new ResidualEdge<>(3, new IntegerLength()));
        graph.addDirectedEdge("4", "8", new ResidualEdge<>(3, new IntegerLength()));
        graph.addDirectedEdge("5", "8", new ResidualEdge<>(3, new IntegerLength()));
        graph.addDirectedEdge("6", "t", new ResidualEdge<>(1, new IntegerLength()));
        graph.addDirectedEdge("7", "t", new ResidualEdge<>(3, new IntegerLength()));
        graph.addDirectedEdge("8", "t", new ResidualEdge<>(4, new IntegerLength()));
        Assert.assertEquals(java.util.Optional.ofNullable(graph.fordFulkerson("s", "t")), java.util.Optional.of(7));
        graph.clear();
        graph.addDirectedEdge("s", "a", new ResidualEdge<>(10, new IntegerLength()));
        graph.addDirectedEdge("s", "b", new ResidualEdge<>(5, new IntegerLength()));
        graph.addDirectedEdge("s", "c", new ResidualEdge<>(15, new IntegerLength()));
        graph.addDirectedEdge("a", "b", new ResidualEdge<>(4, new IntegerLength()));
        graph.addDirectedEdge("b", "c", new ResidualEdge<>(4, new IntegerLength()));
        graph.addDirectedEdge("a", "d", new ResidualEdge<>(9, new IntegerLength()));
        graph.addDirectedEdge("a", "e", new ResidualEdge<>(15, new IntegerLength()));
        graph.addDirectedEdge("b", "e", new ResidualEdge<>(8, new IntegerLength()));
        graph.addDirectedEdge("c", "f", new ResidualEdge<>(16, new IntegerLength()));
        graph.addDirectedEdge("d", "e", new ResidualEdge<>(15, new IntegerLength()));
        graph.addDirectedEdge("d", "t", new ResidualEdge<>(10, new IntegerLength()));
        graph.addDirectedEdge("e", "f", new ResidualEdge<>(15, new IntegerLength()));
        graph.addDirectedEdge("e", "t", new ResidualEdge<>(10, new IntegerLength()));
        graph.addDirectedEdge("f", "t", new ResidualEdge<>(10, new IntegerLength()));
        graph.addDirectedEdge("f", "b", new ResidualEdge<>(6, new IntegerLength()));
        Assert.assertEquals(java.util.Optional.ofNullable(graph.fordFulkerson("s", "t")), java.util.Optional.of(28));
    }
}
