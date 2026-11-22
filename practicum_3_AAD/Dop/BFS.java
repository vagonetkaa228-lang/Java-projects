package practicum_3_AAD.Dop;

import java.util.Arrays;

public class BFS {

    private BFS() { }

    public static BFSResult search(Graph graph, int start) {
        GraphChecks.requireVertex(graph, start);
        int n = graph.vertexCount();
        int[] distance = new int[n];
        int[] parent = new int[n];
        Arrays.fill(distance, -1);
        Arrays.fill(parent, -1);

        Queue queue = new Queue();
        queue.enqueue(start);
        distance[start] = 0;
        while (!queue.isEmpty()) {
            int current = queue.dequeue();
            graph.forEachOutgoing(
                    current,
                    (to, weight) -> {
                        if (distance[to] == -1) {
                            distance[to] = distance[current] + 1;
                            parent[to] = current;
                            queue.enqueue(to);
                        }
                    }
            );
        }

        return new BFSResult(start, distance, parent);
    }
}
