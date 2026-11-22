package practicum_7.app;

import practicum_7.model.CelestialBody;
import practicum_7.model.Pendulum;
import practicum_7.model.Planet;
import practicum_7.model.Moon;
import practicum_7.model.SolarSystemBody;
import practicum_7.service.PendulumService;

import practicum_7.exception.BodyNotFoundException;
import practicum_7.exception.InvalidPhysicsStateException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class example_3 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PendulumService service = new PendulumService(); // Создаем сервис

    public static void main(String[] args) {
        // --- 1. Массив родительского типа ---
        CelestialBody[] bodies = Arrays.stream(SolarSystemBody.values())
                .map(SolarSystemBody::toCelestialBody)
                .toArray(CelestialBody[]::new);


        System.out.println("1. Список небесных тел и расчет периода для нити 1м:");
        for (CelestialBody body : bodies) {
            double period = 2 * Math.PI * Math.sqrt(1.0 / body.getGravity());
            System.out.printf("%s -> Период: %.3f сек%n", body.toString(), period);
        }

        // --- 2. Демонстрация equals и hashCode ---
//        System.out.println("\n2. Проверка equals и hashCode:");
        Planet earth1 = new Planet("Земля", 9.81);
        Planet earth2 = new Planet("Земля", 9.81);
        Moon moon = new Moon("Луна", 1.62, "Земля");

//        System.out.println("earth1.equals(earth2): " + earth1.equals(earth2));
//        System.out.println("hashCode earth1 == earth2: " + (earth1.hashCode() == earth2.hashCode()));
//        System.out.println("earth1.equals(moon): " + earth1.equals(moon));

        // --- 3. Демонстрация record и сортировки ---
        System.out.println("\n3. Сортировка маятников (record + Comparable):");
        List<Pendulum> pendulums = new ArrayList<>();
        pendulums.add(new Pendulum(2.5));
        pendulums.add(new Pendulum(0.4));
        pendulums.add(new Pendulum(1.2));
        Collections.sort(pendulums);
        for (Pendulum p : pendulums) {
            System.out.println("Длина нити: " + p.length() + " м");
        }

        // --- Запуск интерактивной части ---
        System.out.println("\n--- Интерактивный расчет ---");
        runInteractiveMode();
    }

    public static void runInteractiveMode() {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Выберите пункт меню: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.\n");
                continue;
            }

            switch (choice) {
                case 1: performCalculation(); break;
                case 2: printAboutProgram(); break;
                case 3: printAboutDeveloper(); break;
                case 0:
                    running = false;
                    System.out.println("Выход. До свидания!");
                    break;
                default: System.out.println("Ошибка: нет такого пункта.\n");
            }
        }
    }

    private static void performCalculation() {
        try {
            System.out.println("\n--- Расчёт периода ---");
            System.out.print("Введите длину нити (м): ");
            double l = Double.parseDouble(scanner.nextLine().trim());

            // Если введут -5, здесь выбросится InvalidPhysicsStateException
            Pendulum pendulum = new Pendulum(l);

            System.out.print("Где считаем? (Земля / Луна / Юпитер): ");
            String planetName = scanner.nextLine().trim();

            // Если введут "Марс", здесь выбросится BodyNotFoundException
            CelestialBody selected = service.findBody(planetName);

            double period = pendulum.calculatePeriod(selected);
            System.out.printf("Период колебаний на %s: %.3f сек%n", selected.getName(), period);

        } catch (NumberFormatException e) {
            // Ловим стандартное исключение (ввели букву вместо числа)
            System.out.println("Ошибка (Стандартное): Введите число, а не текст!");

        } catch (BodyNotFoundException e) {
            // Ловим наше ПРОВЕРЯЕМОЕ исключение
            System.out.println("Ошибка (Проверяемое): " + e.getMessage());

        } catch (InvalidPhysicsStateException e) {
            // Ловим наше НЕПРОВЕРЯЕМОЕ исключение
            System.out.println("Ошибка (Непроверяемое): " + e.getMessage());

        } catch (IllegalArgumentException e) {
            // Ловим второе стандартное исключение (из конструкторов планет, если там что-то сломается)
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void printMenu() {
        System.out.println("МЕНЮ");
        System.out.println("1 - Выполнить расчёт");
        System.out.println("2 - Информация о программе");
        System.out.println("3 - Информация о разработчике");
        System.out.println("0 - Выход");
    }

    public static void printAboutProgram() {
        System.out.println("\n--- О программе ---");
        System.out.println("Программа считает период колебаний математического маятника.\n");
    }

    public static void printAboutDeveloper() {
        System.out.println("\n--- О разработчике ---");
        System.out.println("Студент: Гулаков Н.А.");
        System.out.println("Группа: РИ-250911\n");
    }
}