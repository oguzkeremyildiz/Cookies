import Cookies.Graph.Edge;
import Cookies.Graph.IntegerLength;
import Cookies.Graph.WeightedGraph;
import Cookies.Tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class WeightedGraphTest {

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
        graph.addDirectedEdge("s", "a", new Edge<>(10, new IntegerLength()));
        graph.addDirectedEdge("s", "c", new Edge<>(10, new IntegerLength()));
        graph.addDirectedEdge("a", "d", new Edge<>(8, new IntegerLength()));
        graph.addDirectedEdge("d", "t", new Edge<>(10, new IntegerLength()));
        graph.addDirectedEdge("a", "b", new Edge<>(4, new IntegerLength()));
        graph.addDirectedEdge("a", "c", new Edge<>(2, new IntegerLength()));
        graph.addDirectedEdge("c", "d", new Edge<>(9, new IntegerLength()));
        graph.addDirectedEdge("b", "t", new Edge<>(10, new IntegerLength()));
        graph.addDirectedEdge("d", "b", new Edge<>(6, new IntegerLength()));
        Assert.assertEquals(java.util.Optional.ofNullable(graph.fordFulkerson("s", "t")), java.util.Optional.of(19));
    }
}
