package practicum_3_AAD.Dop;

import java.util.List;

/** Результат алгоритма Краскала; для связного графа это минимальное остовное дерево. */
public record MinimumSpanningForest(List<WeightedEdge> edges, long totalWeight, int componentCount) {
    public MinimumSpanningForest {
        edges = List.copyOf(edges);
    }

    public boolean isSpanningTree() {
        return componentCount == 1;
    }
}
