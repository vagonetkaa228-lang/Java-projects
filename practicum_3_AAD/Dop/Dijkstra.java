package practicum_3_AAD.Dop;

import java.util.Arrays;
import java.util.Comparator;

/** Алгоритм Дейкстры для поиска кратчайших путей в графах с неотрицательными весами. */
public final class Dijkstra {
    private Dijkstra() { }

    public static DijkstraResult search(Graph graph, int source) {
        GraphChecks.requireVertex(graph, source);
        validateNonNegativeWeights(graph);

        int n = graph.vertexCount();
        long[] distance = new long[n];
        int[] parent = new int[n];
        Arrays.fill(distance, DijkstraResult.UNREACHABLE);
        Arrays.fill(parent, -1);
        distance[source] = 0;

        BinaryMinHeap<Entry> queue = new BinaryMinHeap<>(
                Comparator.comparingLong(Entry::distance).thenComparingInt(Entry::vertex));
        queue.add(new Entry(source, 0));
        while (!queue.isEmpty()) {
            Entry current = queue.removeMin();
            if (current.distance != distance[current.vertex]) continue; // Устаревшая запись в куче.

            graph.forEachOutgoing(current.vertex, (to, weight) -> {
                long candidate;
                try {
                    candidate = Math.addExact(current.distance, weight);
                } catch (ArithmeticException overflow) {
                    throw new ArithmeticException("path length exceeds long range");
                }
                if (candidate < distance[to]) {
                    distance[to] = candidate;
                    parent[to] = current.vertex;
                    queue.add(new Entry(to, candidate));
                }
            });
        }
        return new DijkstraResult(source, distance, parent);
    }

    private static void validateNonNegativeWeights(Graph graph) {
        for (int vertex = 0; vertex < graph.vertexCount(); vertex++) {
            graph.forEachOutgoing(vertex, (to, weight) -> {
                if (weight < 0) {
                    throw new IllegalArgumentException("Dijkstra does not support negative weights");
                }
            });
        }
    }

    private record Entry(int vertex, long distance) { }
}
