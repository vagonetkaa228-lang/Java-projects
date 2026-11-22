package practicum_3_AAD.Dop;

public interface Graph {

    int vertexCount();
    boolean isDirected();
    void addEdge(int from, int to, long weight);
    boolean containsEdge(int from, int to);
    void forEachOutgoing(int from, EdgeConsumer consumer);

}
