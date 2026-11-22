package practicum_3_AAD.Dop;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Алгоритм Краскала для построения минимального остовного леса во взвешенном неориентированном графе. */
public final class Kruskal {
    private Kruskal() { }

    public static MinimumSpanningForest minimumSpanningForest(EdgeGraph edgeGraph) {
        if (edgeGraph == null) {
            throw new IllegalArgumentException("edgeGraph must not be null");
        }
        if (edgeGraph.isDirected()) {
            throw new IllegalArgumentException("Kruskal requires an undirected graph");
        }

        List<WeightedEdge> sortedEdges = new ArrayList<>();
        edgeGraph.forEachEdge(sortedEdges::add);
        sortedEdges.sort(Comparator.comparingLong(WeightedEdge::weight)
                .thenComparingInt(WeightedEdge::from)
                .thenComparingInt(WeightedEdge::to));

        DisjointSetUnion dsu = new DisjointSetUnion(edgeGraph.vertexCount());
        List<WeightedEdge> chosen = new ArrayList<>();
        long totalWeight = 0;
        for (WeightedEdge edge : sortedEdges) {
            if (dsu.union(edge.from(), edge.to())) {
                chosen.add(edge);
                totalWeight = Math.addExact(totalWeight, edge.weight());
            }
        }
        return new MinimumSpanningForest(chosen, totalWeight, dsu.componentCount());
    }
}
