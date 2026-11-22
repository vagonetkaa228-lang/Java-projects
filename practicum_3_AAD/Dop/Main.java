package practicum_3_AAD.Dop;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Graph traversalGraph = GraphExamples.create("graph003");
        BFSResult bfs = BFS.search(traversalGraph, 0);
        System.out.println("BFS distances: " + Arrays.toString(bfs.distances()));
        System.out.println("BFS path 0 -> 8: " + bfs.pathTo(8));
        System.out.println("DFS reachable: " + Arrays.toString(DFS.search(traversalGraph, 0)));

        DijkstraResult shortestPaths = Dijkstra.search(GraphExamples.create("graph004"), 0);
        System.out.println("Dijkstra distances: " + Arrays.toString(shortestPaths.distances()));

        MinimumSpanningForest forest = Kruskal.minimumSpanningForest(
                GraphExamples.createEdgeList("graph007"));
        System.out.println("Kruskal weight: " + forest.totalWeight()
                + ", edges: " + forest.edges());

        TopologicalSortResult topological = TopologicalSort.sort(
                GraphExamples.create("graph009"));
        System.out.println("Topological order: " + topological.order());
    }
}
