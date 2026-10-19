package practicum_2;

import java.util.Scanner;

public class example_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double eps = scanner.nextDouble();

        if (eps <= 0) {
            System.out.println("Введите eps > 0");
            return;
        }

        double prev = 2.0;         // A1
        double curr = 1.0 / prev;  // A2
        int K = 2;

        while (Math.abs(curr - prev) >= eps) {
            prev = curr;
            curr = 1.0 / prev;
            K++;
        }

        System.out.println(K);
        System.out.println(prev);
        System.out.println(curr);

    }
}