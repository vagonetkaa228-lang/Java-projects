package practicum_3_AAD.Dop;


final class GraphChecks {

    private GraphChecks() { }

    static void requireGraph(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph must not be null");
        }
    }

    static void requireVertex(Graph graph, int vertex) {
        requireGraph(graph);
        if (vertex < 0 || vertex >= graph.vertexCount()) {
            throw new IndexOutOfBoundsException("vertex out of range: " + vertex);
        }
    }

}
