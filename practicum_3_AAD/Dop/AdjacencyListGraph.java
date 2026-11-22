package practicum_3_AAD.Dop;

public class AdjacencyListGraph implements Graph {

    private final AdjacencyListNode[] heads;
    private final boolean directed;

    public AdjacencyListGraph(int vertexCount, boolean directed) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("vertexCount must be non-negative");
        }
        this.heads = new AdjacencyListNode[vertexCount];
        this.directed = directed;
    }

    @Override
    public int vertexCount() {
        return this.heads.length;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public void addEdge(int from, int to, long weight) {
        checkVertex(from);
        checkVertex(to);
        this.heads[from] = new AdjacencyListNode(to, weight, this.heads[from]);
        if (!directed) {
            this.heads[to] = new AdjacencyListNode(from, weight, this.heads[to]);
        }
    }

    @Override
    public boolean containsEdge(int from, int to) {
        checkVertex(from);
        checkVertex(to);
        for (AdjacencyListNode node = heads[from]; node != null; node = node.getNext()) {
            if (node.getTo() == to) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void forEachOutgoing(int from, EdgeConsumer consumer) {
        checkVertex(from);
        if (consumer == null) {
            throw new IllegalArgumentException("consumer must not be null");
        }
        for (AdjacencyListNode node = heads[from];
             node != null;
             node = node.getNext()
        ) {
            consumer.accept(node.getTo(), node.getWeight());
        }
    }

    private void checkVertex(int vertex) {
        if (vertex < 0 || vertex >= heads.length) {
            throw new IndexOutOfBoundsException("vertex out of range: " + vertex);
        }
    }
}
