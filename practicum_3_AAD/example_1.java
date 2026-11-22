package practicum_3_AAD;

import practicum_3_AAD.Dop.AdjacencyListGraph;
import practicum_3_AAD.Dop.BFS;
import practicum_3_AAD.Dop.BFSResult;
import practicum_3_AAD.Dop.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class example_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int h = scanner.nextInt();
        int w = scanner.nextInt();
        scanner.nextLine(); // переходим на следующую строку

        char[][] grid = new char[h][w];
        for (int i = 0; i < h; i++) {
            grid[i] = scanner.nextLine().toCharArray();
        }


        int startR = scanner.nextInt();
        int startC = scanner.nextInt() ;

        System.out.println("--- Результат для 4-связности ---");
        solveCave(grid, startR, startC, false);

        System.out.println("\n--- Результат для 8-связности ---");
        solveCave(grid, startR, startC, true);
    }

    private static void solveCave(char[][] grid, int startR, int startC, boolean is8Connected) {
        int h = grid.length;
        int w = grid[0].length;
        int totalVertices = h * w;

        // Проверка, что старт не в стене
        if (grid[startR][startC] == '#') {
            System.out.println(-1);
            return;
        }

        // Создаем неориентированный граф (directed = false)
        Graph graph = new AdjacencyListGraph(totalVertices, false);

        // Задаем направления (4 или 8)
        int[][] dirs = is8Connected
                ? new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}, {-1,-1}, {-1,1}, {1,-1}, {1,1}}
                : new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};

        // Строим граф по карте: каждая свободная клетка - это вершина
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (grid[r][c] == '#') continue; // стена — не вершина

                int id = r * w + c; // Уникальный ID клетки

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Если сосед в пределах карты и это не стена
                    if (nr >= 0 && nr < h && nc >= 0 && nc < w && grid[nr][nc] != '#') {
                        int neighborId = nr * w + nc;

                        // Чтобы не дублировать ребра в неориентированном графе,
                        // добавляем только если id < neighborId
                        if (id < neighborId) {
                            graph.addEdge(id, neighborId, 1);
                        }
                    }
                }
            }
        }

        int startId = startR * w + startC;

        // ЗАПУСКАЕМ BFS
        BFSResult bfs = BFS.search(graph, startId);

        int bestExitId = -1;
        int minDistance = Integer.MAX_VALUE;

        // Ищем ближайший выход по границе
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                // Если это граница и это пол
                if ((r == 0 || r == h - 1 || c == 0 || c == w - 1) && grid[r][c] == '.') {
                    int id = r * w + c;

                    // Используем метод isReachable() из BFSResult
                    if (bfs.isReachable(id)) {
                        int dist = bfs.distanceTo(id);
                        if (dist < minDistance) {
                            minDistance = dist;
                            bestExitId = id;
                        }
                    }
                }
            }
        }

        // Выводим результат
        if (bestExitId == -1) {
            System.out.println(-1);
        } else {
            System.out.println("Расстояние: " + minDistance);

            // ПОЛУЧАЕМ ПУТЬ С ПОМОЩЬЮ МЕТОДА pathTo() 
            List<Integer> pathIds = bfs.pathTo(bestExitId);
            List<String> pathCoords = new ArrayList<>();

            for (int id : pathIds) {
                int r = id / w;
                int c = id % w;
                pathCoords.add("(" + r  + "," + c  + ")");
            }

            System.out.println("Путь: " + String.join(" -> ", pathCoords));
        }
    }
}
