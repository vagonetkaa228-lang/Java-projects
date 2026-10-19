package practicum_1;

import java.util.Scanner;

public class example_4 {
    public static void main(String[] args) {
        System.out.println("Программа считает период колебаний математического маятника.");
        System.out.println("Формула: T = 2π(L/g)^1/2");

        Scanner scanner = new Scanner(System.in);

        // Ввод длины
        System.out.println("Введите длину нити (м):");
        double l = scanner.nextDouble();

        // Валидация длины — сразу, до всего остального
        if (l <= 0) {
            System.out.println("Ошибка: длина должна быть больше нуля.");
            scanner.close();
            return;
        }

        // Ввод планеты
        System.out.println("Где считаем? (Земля / Луна / Юпитер):");
        String object = scanner.next();

        // Определяем g
        double g;
        if (object.equals("Земля")) {
            g = 9.81;
        } else if (object.equals("Луна")) {
            g = 1.62;
        } else if (object.equals("Юпитер")) {
            g = 24.79;
        } else {

            System.out.println("Ошибка: неизвестная планета: " + object);
            scanner.close();
            return;
        }

        // 5. Расчёт
        double T = 2 * Math.PI * Math.sqrt(l / g);

        // 6. Вывод
        System.out.printf("Период колебаний: %.3f", T);
        scanner.close();
    }
}