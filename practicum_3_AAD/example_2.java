package practicum_3_AAD;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import practicum_3_AAD.Dop.Graph;
import practicum_3_AAD.Dop.AdjacencyListGraph;
import practicum_3_AAD.Dop.BinaryMinHeap;

public class example_2 {

    // Вспомогательный класс для хранения пары (вершина, расстояние).
    // Он нужен, чтобы класть эти пары в кучу 
    static class State {
        int vertex;
        long dist;

        State(int vertex, long dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        // 1. Чтение входных данных
        int n = scanner.nextInt(); // Количество вершин
        int m = scanner.nextInt(); // Количество ребер
        int s = scanner.nextInt(); // Старт
        int t = scanner.nextInt(); // Финиш

        // 2. ИСПОЛЬЗУЕМ ГРАФ ПРЕПОДАВАТЕЛЯ
        // Передаем true, так как граф в задаче ОРИЕНТИРОВАННЫЙ
        Graph graph = new AdjacencyListGraph(n, true);

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            long w = scanner.nextLong();
            graph.addEdge(u, v, w); // Добавляем ребро
        }

        // 3. Подготовка массивов для Дейкстры 
        long INF = Long.MAX_VALUE;
        long[] dist1 = new long[n]; // Самый короткий путь
        long[] dist2 = new long[n]; // Второй по короткости
        Arrays.fill(dist1, INF);
        Arrays.fill(dist2, INF);

        // 4. ИСПОЛЬЗУЕМ КУЧУ  (BinaryMinHeap)
        // Передаем компаратор, который говорит куче: "сначала отдавай тот State, где dist меньше"
        BinaryMinHeap<State> pq = new BinaryMinHeap<>(
                Comparator.comparingLong(state -> state.dist)
        );

        // Инициализация старта
        dist1[s] = 0;
        pq.add(new State(s, 0));

        // 5. Модифицированный алгоритм Дейкстры 
        while (!pq.isEmpty()) {
            State curr = pq.removeMin(); // Достаем минимальный элемент из кучи
            long d = curr.dist;
            int u = curr.vertex;

            // Если текущее расстояние хуже, чем второй известный путь, нет смысла идти дальше
            if (d > dist2[u]) continue;


            // Лямбда (to, weight) - это и есть
            graph.forEachOutgoing(u, (to, weight) -> {
                long nd = d + weight; // Новое расстояние

                if (nd < dist1[to]) {
                    // Нашли новый самый короткий путь
                    dist2[to] = dist1[to]; // Старый рекорд сдвигаем на 2-е место
                    dist1[to] = nd;        // Новый рекорд на 1-е место
                    pq.add(new State(to, nd));
                } else if (nd > dist1[to] && nd < dist2[to]) {
                    // Нашли новый второй по короткости путь
                    dist2[to] = nd;
                    pq.add(new State(to, nd));
                } else if (nd == dist1[to] && dist2[to] == INF) {
                    // Нашли ДРУГОЙ маршрут, но такой же длины, как самый короткий
                    dist2[to] = nd;
                    pq.add(new State(to, nd));
                }
            });
        }

        // 6. Вывод результата
        long first = (dist1[t] == INF) ? -1 : dist1[t];
        long second = (dist2[t] == INF) ? -1 : dist2[t];

        System.out.println("first=" + first + " second=" + second);
    }
}
