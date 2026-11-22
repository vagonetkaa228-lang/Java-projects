package practicum_3_AAD;

import practicum_3_AAD.Dop.MinimumSpanningForest;
import practicum_3_AAD.Dop.EdgeListGraph;
import practicum_3_AAD.Dop.Kruskal;
import practicum_3_AAD.Dop.WeightedEdge;

import practicum_3_AAD.Dop.EdgeGraph;
import java.util.Scanner;


public class example_3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt(); // Количество вершин (городов)
        int m = scanner.nextInt(); // Количество ребер (возможных кабелей)

        // 1. ИСПОЛЬЗУЕМ ГРАФ  для списка ребер
        // directed = false, так как кабель можно тянуть в обе стороны
        EdgeGraph graph = new EdgeListGraph(n, false);

        // 2. Читаем кабели и добавляем их в граф
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            long w = scanner.nextLong();
            graph.addEdge(u, v, w);
        }

        // 3. ЗАПУСКАЕМ АЛГОРИТМ КРАСКАЛА
        // Метод вернет объект MinimumSpanningForest (в нашем случае это дерево)
        MinimumSpanningForest mst = Kruskal.minimumSpanningForest(graph);

        // 4. Ищем максимальный вес среди выбранных ребер
        long maxWeight = 0;
        for (WeightedEdge edge : mst.edges()) {
            if (edge.weight() > maxWeight) {
                maxWeight = edge.weight();
            }
        }

        
        System.out.println("maxSelected = " + maxWeight);
    }
}
