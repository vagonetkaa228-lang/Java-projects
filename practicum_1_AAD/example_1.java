package practicum_1_AAD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class example_1 {
    // =========================
    // MERGE SORT
    // =========================

    static final Random RND = new Random(42);
    static final int RUNS = 5;
    static final int WARMUP = 3;

    // Квадратичная — до 32k, выше слишком долго
    static final int[] N_QUADRATIC = {10, 50, 100, 500, 1000, 2000, 4000, 8000, 16000, 32000};
    // n log n — до 100k, она быстрая
    static final int[] N_NLOGN = {10, 50, 100, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 100000};

    static PrintWriter OUT;

    public static void main(String[] args) throws IOException {
        OUT = new PrintWriter("results.csv");
        OUT.println("algo;case;n;t1;t2;t3;t4;t5");

        for (String c : new String[]{"best", "average", "worst"}) {
            for (int n : N_QUADRATIC) bench("insertion", c, n);
            for (int n : N_NLOGN)     bench("merge",     c, n);
        }

        OUT.close();
        System.out.println("Готово! Файл results.csv лежит в корне проекта.");
    }

    static void bench(String algo, String caseName, int n) {
        int[] base = generate(n, caseName);


        for (int i = 0; i < WARMUP; i++) {
            run(algo, Arrays.copyOf(base, base.length));
        }

        // 5 настоящих замеров
        OUT.print(algo + ";" + caseName + ";" + n);
        for (int r = 0; r < RUNS; r++) {
            int[] copy = Arrays.copyOf(base, base.length);
            long start = System.nanoTime();
            run(algo, copy);
            long elapsed = System.nanoTime() - start;
            OUT.print(";" + elapsed);
        }
        OUT.println();
        System.out.println("  done: " + algo + " " + caseName + " n=" + n);
    }

    static void run(String algo, int[] a) {
        switch (algo) {
            case "insertion" -> insertionSort(a);
            case "merge"     -> mergeSort(a, 0, a.length - 1);
            default -> throw new IllegalArgumentException(algo);
        }
    }

    static int[] generate(int n, String caseName) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = RND.nextInt(1_000_000);
        switch (caseName) {
            case "best" -> Arrays.sort(a);
            case "worst" -> {
                Arrays.sort(a);
                for (int i = 0, j = a.length - 1; i < j; i++, j--) {
                    int t = a[i]; a[i] = a[j]; a[j] = t;
                }
            }
            case "average" -> { /* оставляем случайный порядок */ }
        }
        return a;
    }

    // O(n^2): лучший случай O(n) (если уже отсортирован), худший O(n^2)
    static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // O(n log n) во всех случаях
    static void mergeSort(int[] a, int l, int r) {
        if (l >= r) return;
        int m = (l + r) >>> 1;
        mergeSort(a, l, m);
        mergeSort(a, m + 1, r);
        int[] tmp = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) tmp[k++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        while (i <= m) tmp[k++] = a[i++];
        while (j <= r) tmp[k++] = a[j++];
        System.arraycopy(tmp, 0, a, l, tmp.length);
    }
}