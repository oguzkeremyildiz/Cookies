import Cookies.Graph.Edge;
import Cookies.Graph.IntegerLength;
import Cookies.Graph.WeightedGraph;
import org.junit.Assert;
import org.junit.Test;

public class WeightedGraphTest {

    @Test
    public void testKruskal() {
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
    }
}
