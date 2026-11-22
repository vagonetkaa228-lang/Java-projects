package practicum_3_AAD;

import practicum_3_AAD.Dop.Graph;
import practicum_3_AAD.Dop.AdjacencyListGraph;
import practicum_3_AAD.Dop.TopologicalSortResult;
import practicum_3_AAD.Dop.TopologicalSort;

import java.util.List;
import java.util.Scanner;

public class example_4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt(); // Количество пакетов
        int m = scanner.nextInt(); // Количество зависимостей


        // directed = true, так как зависимости направленные (А ждет Б)
        Graph graph = new AdjacencyListGraph(n, true);

        // 2. Читаем зависимости и добавляем ребра
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt(); // Тот, кого ждем (dependency)
            int v = scanner.nextInt(); // Тот, кто ждет (package)
            // Добавляем ребро u -> v. Вес не важен, ставим 1.
            graph.addEdge(u, v, 1);
        }

        // 3. ЗАПУСКАЕМ АЛГОРИТМ 
        TopologicalSortResult result = TopologicalSort.sort(graph);

        // 4. Проверяем, есть ли цикл
        if (!result.acyclic()) {
            // Если граф содержит цикл (acyclic == false)
            System.out.println("CYCLE");
        } else {
            // Если цикла нет, выводим порядок установки
            List<Integer> order = result.order();
            for (int i = 0; i < order.size(); i++) {
                System.out.print(order.get(i));
                // Печатаем пробел между пакетами (кроме последнего)
                if (i < order.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
