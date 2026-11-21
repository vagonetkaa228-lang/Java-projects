package practicum_2_AAD;

import java.util.Arrays;

// Выбранный метод: Counting Sort (Сортировка подсчетом).
// Почему: Данные — целые числа (количество товаров) с известным и
// ограниченным диапазоном (от 0 до 10 000). Bucket Sort не подойдет,
// так как нет гарантии равномерного распределения, а Radix Sort сделает
// слишком много лишних проходов для таких небольших чисел. Counting Sort
// идеально использует фиксированный диапазон для достижения линейного времени.
//
// Сложность:
// Время: O(N + K), где K — диапазон значений (10 000). Так как K фиксировано,
// сложность стремится к O(N).
// Память: O(K) (для массива счетчиков размером 10 001).

public class example_2 {

    public static void main(String[] args) {
        // Пример 1 из задания
        int[] arr1 = {10000, 0, 25, 25};
        System.out.println("Пример 1 до:    " + Arrays.toString(arr1));
        sort(arr1);
        System.out.println("Пример 1 после: " + Arrays.toString(arr1));

        System.out.println("-------------------");

        // Пример 2 из задания
        int[] arr2 = {7, 2, 7, 4};
        System.out.println("Пример 2 до:    " + Arrays.toString(arr2));
        sort(arr2);
        System.out.println("Пример 2 после: " + Arrays.toString(arr2));
    }

    public static void sort(int[] arr) {
        if (arr.length == 0) return;

        // 1. Находим минимальное и максимальное значения
        int min = arr[0];
        int max = arr[0];
        for (int val : arr) {
            min = Math.min(min, val);
            max = Math.max(max, val);
        }

        // 2. Вычисляем диапазон
        int range = max - min + 1;

        // 3. Создаем массив для подсчета частот
        int[] count = new int[range];

        // 4. Считаем частоту каждого числа
        for (int val : arr) {
            int offset = val - min; // Сдвиг, чтобы не создавать массив от 0
            count[offset]++;
        }

        // 5. Восстанавливаем отсортированный массив
        int index = 0;
        for (int offset = 0; offset < count.length; offset++) {
            for (int repeat = 0; repeat < count[offset]; repeat++) {
                arr[index++] = offset + min; // Возвращаем исходное значение
            }
        }
    }
}