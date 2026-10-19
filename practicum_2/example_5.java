package practicum_2;

import java.util.Scanner;

public class example_5 {

    private static final Scanner scanner = new Scanner(System.in);

    //меню
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Выберите пункт меню: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    calculatePeriod();
                    break;
                case 2:
                    printAboutProgram();
                    break;
                case 3:
                    printAboutDeveloper();
                    break;
                case 0:
                    running = false;
                    System.out.println("Выход. До свидания!");
                    break;
                default:
                    System.out.println("Ошибка: нет такого пункта. Попробуйте снова.\n");
            }
        }


    }

    // ПУНКТЫ МЕНЮ

    /** Пункт 1 — расчёт периода */
    public static void calculatePeriod() {
        System.out.println("\n--- Расчёт периода колебаний маятника ---");

        double l = readPositiveDouble("Введите длину нити (м): ");
        double g = readGravity();

        double T = 2 * Math.PI * Math.sqrt(l / g);
        System.out.printf("Период колебаний: %.3f с%n%n", T);
    }

    /** Пункт 2 — о программе */
    public static void printAboutProgram() {
        System.out.println("\n--- О программе ---");
        System.out.println("Программа считает период колебаний математического маятника.");
        System.out.println("Формула: T = 2π√(L/g)");
        System.out.println("Поддерживаемые планеты: Земля, Луна, Юпитер.\n");
    }

    /** Пункт 3 — о разработчике */
    public static void printAboutDeveloper() {
        System.out.println("\n--- О разработчике ---");
        System.out.println("Студент: Гулаков Н.А.");
        System.out.println("Группа: РИ-250911");
        System.out.println("Лабораторная работа №2\n");
    }

    //ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ

    public static void printMenu() {
        System.out.println("МЕНЮ");
        System.out.println("1 - Выполнить расчёт");
        System.out.println("2 - Информация о программе");
        System.out.println("3 - Информация о разработчике");
        System.out.println("0 - Выход");

    }

    /** Чтение целого с защитой от неверного ввода */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit") || line.equals("0")) {
                return 0;   // команда выхода
            }

            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число или 0 для выхода.\n");
            }
        }
    }
        /** Чтение положительного double с защитой от неверного ввода */
        public static double readPositiveDouble(String prompt) {
            while (true) {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                try {
                    double value = Double.parseDouble(line);
                    if (value <= 0) {
                        System.out.println("Ошибка: число должно быть больше нуля. Попробуйте снова.\n");
                        continue;
                    }
                    return value;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите корректное число (например, 1.5).\n");
                }
            }
        }


    // Выбор планеты с защитой от неверного ввода
    public static double readGravity() {
        while (true) {
            System.out.print("Где считаем? (Земля / Луна / Юпитер): ");
            String object = scanner.nextLine().trim();

            switch (object) {
                case "Земля":   return 9.81;
                case "Луна":    return 1.62;
                case "Юпитер":  return 24.79;
                default:
                    System.out.println("Ошибка: неизвестная планета. Попробуйте снова.\n");
            }
        }
    }
}