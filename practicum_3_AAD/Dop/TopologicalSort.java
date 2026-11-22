package practicum_3_AAD.Dop;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/** Детерминированная топологическая сортировка алгоритмом Кана для ориентированных графов. */
public final class TopologicalSort {
    private TopologicalSort() { }

    public static TopologicalSortResult sort(Graph graph) {
        GraphChecks.requireGraph(graph);
        if (!graph.isDirected()) {
            throw new IllegalArgumentException("topological sort requires a directed graph");
        }

        int n = graph.vertexCount();
        int[] indegree = new int[n];
        for (int vertex = 0; vertex < n; vertex++) {
            graph.forEachOutgoing(vertex, (to, weight) -> indegree[to]++);
        }

        PriorityQueue<Integer> ready = new PriorityQueue<>();
        for (int vertex = 0; vertex < n; vertex++) {
            if (indegree[vertex] == 0) ready.add(vertex);
        }

        List<Integer> order = new ArrayList<>(n);
        while (!ready.isEmpty()) {
            int vertex = ready.remove();
            order.add(vertex);
            graph.forEachOutgoing(vertex, (to, weight) -> {
                if (--indegree[to] == 0) ready.add(to);
            });
        }
        return new TopologicalSortResult(order, order.size() == n);
    }
}
