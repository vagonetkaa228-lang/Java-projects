package practicum_3_AAD.Dop;

/**
 * Именованные учебные графы для обходов и следующих алгоритмов.
 * Каждый вызов фабрики возвращает новый независимый граф.
 */
public final class GraphExamples {

    private GraphExamples() {
        // Только статические фабрики.
    }

    /** Возвращает граф по имени: graph001 ... graph011. */
    public static Graph create(String name) {
        switch (name) {
            case "graph001": return graph001DfsTree();
            case "graph002": return graph002DfsBranches();
            case "graph003": return graph003BfsLevels();
            case "graph004": return graph004DijkstraRoutes();
            case "graph005": return graph005DijkstraAlternative();
            case "graph006": return graph006DijkstraDisconnected();
            case "graph007": return graph007KruskalNetwork();
            case "graph008": return graph008KruskalForest();
            case "graph009": return graph009TopologicalBuild();
            case "graph010": return graph010TopologicalBranches();
            case "graph011": return graph011TopologicalCycle();
            default: throw new IllegalArgumentException("Unknown graph: " + name);
        }
    }

    /** Создаёт списочное представление графов для Краскала: graph007 или graph008. */
    public static EdgeGraph createEdgeList(String name) {
        switch (name) {
            case "graph007": return graph007KruskalNetworkEdgeList();
            case "graph008": return graph008KruskalForestEdgeList();
            default: throw new IllegalArgumentException("No edge-list example: " + name);
        }
    }

    /** Дерево для первого знакомства с DFS. */
    public static Graph graph001DfsTree() {
        AdjacencyListGraph graph = new AdjacencyListGraph(7, false);
        add(graph, 0, 1, 1); add(graph, 0, 2, 1);
        add(graph, 1, 3, 1); add(graph, 1, 4, 1);
        add(graph, 2, 5, 1); add(graph, 2, 6, 1);
        return graph;
    }

    /** Граф с циклом и перемычкой для демонстрации множества посещённых вершин в DFS. */
    public static Graph graph002DfsBranches() {
        AdjacencyListGraph graph = new AdjacencyListGraph(8, false);
        add(graph, 0, 1, 1); add(graph, 0, 2, 1); add(graph, 1, 3, 1);
        add(graph, 2, 3, 1); add(graph, 2, 4, 1); add(graph, 3, 5, 1);
        add(graph, 4, 6, 1); add(graph, 5, 7, 1); add(graph, 6, 7, 1);
        return graph;
    }

    /** Уровневый граф для сравнения порядка BFS и DFS. */
    public static Graph graph003BfsLevels() {
        AdjacencyListGraph graph = new AdjacencyListGraph(9, false);
        add(graph, 0, 1, 1); add(graph, 0, 2, 1); add(graph, 0, 3, 1);
        add(graph, 1, 4, 1); add(graph, 1, 5, 1); add(graph, 2, 6, 1);
        add(graph, 3, 7, 1); add(graph, 3, 8, 1); add(graph, 5, 8, 1);
        return graph;
    }

    /** Дейкстра на направленном графе с несколькими маршрутами. */
    public static Graph graph004DijkstraRoutes() {
        AdjacencyListGraph graph = new AdjacencyListGraph(6, true);
        add(graph, 0, 1, 4); add(graph, 0, 2, 1); add(graph, 2, 1, 2);
        add(graph, 1, 3, 1); add(graph, 2, 3, 5); add(graph, 3, 4, 3);
        add(graph, 1, 4, 10); add(graph, 4, 5, 2);
        return graph;
    }

    /** Дейкстра с устаревающими записями в куче. */
    public static Graph graph005DijkstraAlternative() {
        AdjacencyListGraph graph = new AdjacencyListGraph(5, true);
        add(graph, 0, 1, 10); add(graph, 0, 2, 3); add(graph, 2, 1, 1);
        add(graph, 2, 3, 2); add(graph, 3, 1, 2); add(graph, 1, 4, 4);
        add(graph, 3, 4, 8);
        return graph;
    }

    /** Дейкстра с недостижимыми вершинами 4 и 5. */
    public static Graph graph006DijkstraDisconnected() {
        AdjacencyListGraph graph = new AdjacencyListGraph(6, true);
        add(graph, 0, 1, 3); add(graph, 1, 2, 4); add(graph, 0, 2, 10);
        add(graph, 2, 3, 2);
        return graph;
    }

    /** Связная сеть для одного минимального остовного дерева. */
    public static Graph graph007KruskalNetwork() {
        AdjacencyListGraph graph = new AdjacencyListGraph(6, false);
        add(graph, 0, 1, 4); add(graph, 0, 2, 1); add(graph, 1, 2, 2);
        add(graph, 1, 3, 5); add(graph, 2, 3, 3); add(graph, 2, 4, 8);
        add(graph, 3, 4, 2); add(graph, 4, 5, 1); add(graph, 3, 5, 7);
        return graph;
    }

    /** Несвязная сеть: Краскал возвращает минимальный остовный лес. */
    public static Graph graph008KruskalForest() {
        AdjacencyListGraph graph = new AdjacencyListGraph(7, false);
        add(graph, 0, 1, 2); add(graph, 1, 2, 3); add(graph, 0, 2, 5);
        add(graph, 3, 4, 1); add(graph, 4, 5, 4); add(graph, 3, 5, 6);
        return graph;
    }

    /** Ориентированный ациклический граф зависимостей сборки. */
    public static Graph graph009TopologicalBuild() {
        AdjacencyListGraph graph = new AdjacencyListGraph(7, true);
        add(graph, 0, 2, 1); add(graph, 0, 3, 1); add(graph, 1, 3, 1);
        add(graph, 1, 4, 1); add(graph, 2, 5, 1); add(graph, 3, 5, 1);
        add(graph, 4, 6, 1); add(graph, 5, 6, 1);
        return graph;
    }

    /** Ориентированный ациклический граф с параллельными ветвями и несколькими начальными вершинами. */
    public static Graph graph010TopologicalBranches() {
        AdjacencyListGraph graph = new AdjacencyListGraph(8, true);
        add(graph, 0, 1, 1); add(graph, 0, 3, 1); add(graph, 2, 4, 1);
        add(graph, 2, 5, 1); add(graph, 4, 6, 1); add(graph, 5, 6, 1);
        add(graph, 6, 7, 1);
        return graph;
    }

    /** Граф с циклом: топологический порядок построить нельзя. */
    public static Graph graph011TopologicalCycle() {
        AdjacencyListGraph graph = new AdjacencyListGraph(5, true);
        add(graph, 0, 1, 1); add(graph, 1, 2, 1); add(graph, 2, 3, 1);
        add(graph, 3, 1, 1); add(graph, 3, 4, 1);
        return graph;
    }

    /** Списочное представление связной сети для алгоритма Краскала. */
    public static EdgeListGraph graph007KruskalNetworkEdgeList() {
        EdgeListGraph graph = new EdgeListGraph(6, false);
        add(graph, 0, 1, 4); add(graph, 0, 2, 1); add(graph, 1, 2, 2);
        add(graph, 1, 3, 5); add(graph, 2, 3, 3); add(graph, 2, 4, 8);
        add(graph, 3, 4, 2); add(graph, 4, 5, 1); add(graph, 3, 5, 7);
        return graph;
    }

    /** Списочное представление несвязной сети для минимального остовного леса. */
    public static EdgeListGraph graph008KruskalForestEdgeList() {
        EdgeListGraph graph = new EdgeListGraph(7, false);
        add(graph, 0, 1, 2); add(graph, 1, 2, 3); add(graph, 0, 2, 5);
        add(graph, 3, 4, 1); add(graph, 4, 5, 4); add(graph, 3, 5, 6);
        return graph;
    }

    private static void add(AdjacencyListGraph graph, int from, int to, long weight) {
        graph.addEdge(from, to, weight);
    }

    private static void add(EdgeListGraph graph, int from, int to, long weight) {
        graph.addEdge(from, to, weight);
    }
}
