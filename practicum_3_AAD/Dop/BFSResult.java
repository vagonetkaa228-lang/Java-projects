package practicum_3_AAD.Dop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Расстояния и ссылки на родителей в дереве обхода в ширину от заданной вершины. */
public final class BFSResult {
    private final int source;
    private final int[] distances;
    private final int[] parents;

    BFSResult(int source, int[] distances, int[] parents) {
        this.source = source;
        this.distances = distances.clone();
        this.parents = parents.clone();
    }

    public int source() { return source; }
    public int vertexCount() { return distances.length; }
    public int distanceTo(int vertex) {
        checkVertex(vertex);
        return distances[vertex];
    }
    public int parentOf(int vertex) {
        checkVertex(vertex);
        return parents[vertex];
    }
    public boolean isReachable(int vertex) { return distanceTo(vertex) >= 0; }
    public int[] distances() { return distances.clone(); }
    public int[] parents() { return parents.clone(); }

    /** Возвращает пустой список, если целевая вершина недостижима. */
    public List<Integer> pathTo(int destination) {
        checkVertex(destination);
        if (!isReachable(destination)) return List.of();
        List<Integer> path = new ArrayList<>();
        for (int at = destination; at != -1; at = parents[at]) path.add(at);
        Collections.reverse(path);
        return List.copyOf(path);
    }

    private void checkVertex(int vertex) {
        if (vertex < 0 || vertex >= distances.length) {
            throw new IndexOutOfBoundsException("vertex out of range: " + vertex);
        }
    }
}
