
package practicum_3;

import java.util.Scanner;

public class example_4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число строк: ");
        int M = scanner.nextInt();

        System.out.println("Введите количество столбцов: ");

        int N = scanner.nextInt();

        // 1. Создаем и заполняем матрицу
        int[][] matrix = createMatrix(M, N);

        // 2. Выводим матрицу на экран для проверки
        printMatrix(matrix);
    }


     //Метод создает матрицу M x N и заполняет её по условию.

    public static int[][] createMatrix(int m, int n) {
        // Создаем пустую матрицу размером m строк и n столбцов
        int[][] matrix = new int[m][n];

        // Проходим по каждой строке (i от 0 до M-1)
        for (int i = 0; i < m; i++) {
            // Проходим по каждому столбцу (j от 0 до N-1)
            for (int j = 0; j < n; j++) {
                // В задании столбцы нумеруются с 1, поэтому используем (j + 1)
                // Все элементы j-го столбца равны 5 * j
                matrix[i][j] = 5 * (j + 1);
            }
        }
        return matrix;
    }


     // Метод красиво печатает матрицу на экран.

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t"); // \t - табуляция для ровных столбцов
            }
            System.out.println(); // Переход на новую строку после каждого ряда
        }
    }
}