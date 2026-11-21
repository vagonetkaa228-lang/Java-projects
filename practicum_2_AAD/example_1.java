package practicum_2_AAD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Выбранный метод: Bucket Sort (Блочная сортировка).
// Почему: Данные — вещественные числа (double), равномерно распределенные.
// Сортировка подсчетом (Counting) не подойдет (бесконечный диапазон),
// Radix — неудобен для дробей. Bucket Sort идеально превращает числа
// в индексы корзин через умножение на N.
//
// Сложность:
// Время: O(N) в среднем (при равномерном распределении).
// Память: O(N) (для хранения корзин).


public class example_1 {

        public static void main(String[] args) {
            double[] arr = {0.1, 0, 0.5, 0.25, 0.15, 0.65, 0.8};
            System.out.println(Arrays.toString(arr));
            sort(arr);
            System.out.println(Arrays.toString(arr));
        }

        public static void sort(double[] arr) {
            if (arr.length == 0) return;

            int s = arr.length;
            List<List<Double>> buckets = new ArrayList<>(s);
            for (int i = 0; i < s; i++) {
                buckets.add(new ArrayList<>());
            }

            for (double val : arr) {
                if (!(val >= 0 && val < 1.0)) {
                    return;
                }
                int index = Math.min(s - 1, (int) (val * s));
                buckets.get(index).add(val);
            }

            int index = 0;
            for (List<Double> bucket : buckets) {
                insertionSort(bucket);
                for (double val : bucket) {
                    arr[index++] = val;
                }
            }
        }

        private static void insertionSort(List<Double> bucket) {
            for (int i = 1; i < bucket.size(); i++) {
                double key = bucket.get(i);
                int j = i - 1;
                while (j >= 0 && bucket.get(j) > key) {
                    bucket.set(j + 1, bucket.get(j));
                    j--;
                }
                bucket.set(j + 1, key);
            }
        }
    }

