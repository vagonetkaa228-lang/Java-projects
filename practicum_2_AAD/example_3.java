package practicum_2_AAD;

import java.util.Arrays;

// Выбранный метод: Radix Sort (Поразрядная сортировка).
// Почему: Огромное количество элементов (миллионы) и целочисленный тип.
// Обычные сортировки (O(N log N)) сделают сотни миллионов операций сравнения,
// что долго. Counting Sort потребует массив на 100 млн ячеек (сотни мегабайт памяти).
// Radix Sort сделает всего 8 быстрых проходов по разрядам, работая линейно
// и потребляя минимум памяти.
//
// Сложность:
// Время: O(d * N), где d — число разрядов (для 99 999 999 это 8).
// Фактически стремится к O(N).
// Память: O(N) (для временного массива output).

public class example_3 {

        public static void main(String[] args) {
            // Пример 1 из задания
            int[] arr1 = {500, 12, 99999999, 42};
            System.out.println("Пример 1 до:    " + Arrays.toString(arr1));
            radixSort(arr1);
            System.out.println("Пример 1 после: " + Arrays.toString(arr1));

            System.out.println("-------------------");

            // Пример 2 из задания
            int[] arr2 = {101, 10, 1};
            System.out.println("Пример 2 до:    " + Arrays.toString(arr2));
            radixSort(arr2);
            System.out.println("Пример 2 после: " + Arrays.toString(arr2));
        }

        public static void radixSort(int[] arr) {
            if (arr == null || arr.length == 0) return;

            // 1. Находим максимальное число, чтобы узнать количество разрядов
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }
            }

            // 2. Проходим по каждому разряду (1, 10, 100, 1000...)
            // exp = 1 (единицы), exp = 10 (десятки) и т.д.
            for (int exp = 1; max / exp > 0; exp *= 10) {
                countingSortByDigit(arr, exp);
            }
        }

        // Стабильная сортировка подсчетом для конкретного разряда (exp)
        private static void countingSortByDigit(int[] arr, int exp) {
            int n = arr.length;
            int[] output = new int[n]; // Вспомогательный массив для результата
            int[] count = new int[10]; // Массив для подсчета цифр от 0 до 9

            // Считаем частоту каждой цифры в текущем разряде
            for (int i = 0; i < n; i++) {
                int digit = (arr[i] / exp) % 10;
                count[digit]++;
            }

            // Меняем count[i] так, чтобы он содержал реальную позицию
            // этой цифры в output[]
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            // Строим выходной массив (идем с конца для стабильности)
            for (int i = n - 1; i >= 0; i--) {
                int digit = (arr[i] / exp) % 10;
                output[count[digit] - 1] = arr[i];
                count[digit]--;
            }

            // Копируем отсортированный массив обратно в arr
            System.arraycopy(output, 0, arr, 0, n);
        }
    }

