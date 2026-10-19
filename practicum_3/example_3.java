package practicum_3;

import java.util.Arrays;
import java.util.Scanner;

public class example_3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива N: ");
        int n = scanner.nextInt();

        int[] mass = new int[n];
        System.out.println("Введите " + n + " целых чисел:");
        for (int i = 0; i < n; i++) {
            mass[i] = scanner.nextInt();
        }

        int[] b = buildB(mass);

        System.out.println("Исходный массив A: " + Arrays.toString(mass));
        System.out.println("Массив B:          " + Arrays.toString(b));

    }

    public static int[] buildB(int[] a) {
        int[] b = new int[a.length];
        int sum = 0;

        for (int i = a.length - 1; i >= 0; i--) {
            sum += a[i];
            b[i] = sum;
        }

        return b;
    }
}