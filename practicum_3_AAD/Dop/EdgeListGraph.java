package practicum_3_AAD.Dop;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/** Граф, который хранит рёбра единым списком без списков смежности. */
public final class EdgeListGraph implements EdgeGraph {
    private final int vertexCount;
    private final boolean directed;
    private final List<WeightedEdge> edges = new ArrayList<>();

    public EdgeListGraph(int vertexCount, boolean directed) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("vertexCount must be non-negative");
        }
        this.vertexCount = vertexCount;
        this.directed = directed;
    }

    @Override
    public int vertexCount() { return vertexCount; }

    @Override
    public boolean isDirected() { return directed; }

    @Override
    public void addEdge(int from, int to, long weight) {
        checkVertex(from);
        checkVertex(to);
        edges.add(new WeightedEdge(from, to, weight));
    }

    @Override
    public boolean containsEdge(int from, int to) {
        checkVertex(from);
        checkVertex(to);
        for (WeightedEdge edge : edges) {
            if (edge.from() == from && edge.to() == to) return true;
            if (!directed && edge.from() == to && edge.to() == from) return true;
        }
        return false;
    }

    /** Перебирает смежные рёбра сканированием общего списка рёбер. */
    @Override
    public void forEachOutgoing(int from, EdgeConsumer consumer) {
        checkVertex(from);
        if (consumer == null) {
            throw new IllegalArgumentException("consumer must not be null");
        }
        for (WeightedEdge edge : edges) {
            if (edge.from() == from) {
                consumer.accept(edge.to(), edge.weight());
            } else if (!directed && edge.to() == from) {
                consumer.accept(edge.from(), edge.weight());
            }
        }
    }

    @Override
    public void forEachEdge(Consumer<WeightedEdge> consumer) {
        if (consumer == null) {
            throw new IllegalArgumentException("consumer must not be null");
        }
        edges.forEach(consumer);
    }

    private void checkVertex(int vertex) {
        if (vertex < 0 || vertex >= vertexCount) {
            throw new IndexOutOfBoundsException("vertex out of range: " + vertex);
        }
    }
}
