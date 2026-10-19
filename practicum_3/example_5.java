package practicum_3;

import java.util.Scanner;

public class example_5 {

    private static final Scanner scanner = new Scanner(System.in); // Глобальный сканер для всего класса

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Выберите пункт меню: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim()); // Чтение строки, удаление пробелов и преобразование в число
            } catch (NumberFormatException e) { // Если введено не число, ловим ошибку
                System.out.println("Ошибка: введите число.\n"); // Вывод сообщения об ошибке
                continue; // Пропустить остаток цикла и вернуться в начало меню
            }

            switch (choice) {
                case 1:
                    double[] params = inputParameters(); // Получаем массив параметров от метода ввода
                    if (validateParameters(params)) { // Проверяем корректность полученных данных
                        double T = calculatePeriod(params); // Вычисляем период, передавая массив
                        printResult(T);
                    }
                    break;
                case 2: // Если выбран пункт 2
                    printAboutProgram(); // Вызов информации о программе
                    break;
                case 3: // Если выбран пункт 3
                    printAboutDeveloper(); // Вызов информации о разработчике
                    break;
                case 0: // Если выбран пункт 0 (Выход)
                    running = false;
                    System.out.println("Выход. До свидания!");
                    break;
                default: // Если ввели число, которого нет в меню
                    System.out.println("Ошибка: нет такого пункта. Попробуйте снова.\n");
            }
        }

    }

    //МЕТОДЫ ДЛЯ ПУНКТА 1

    public static double[] inputParameters() { // Метод ВВОДА: возвращает массив с числами
        System.out.println("\n--- Расчёт периода колебаний маятника ---");
        double l = readPositiveDouble("Введите длину нити (м): ");
        double g = readGravity();
        return new double[]{l, g}; // Возвращаем массив из двух элементов [0] и [1]
    } // Конец метода ввода

    public static boolean validateParameters(double[] params) { // Метод ПРОВЕРКИ: принимает массив
        double l = params[0]; // Извлекаем длину из массива
        double g = params[1]; // Извлекаем гравитацию из массива

        if (l <= 0) { // Проверка длины
            System.out.println("Ошибка проверки: Длина должна быть больше нуля.");
            return false; // Возвращаем false (данные не прошли)
        } // Конец if
//        if (g <= 0) { // Проверка гравитации
//            System.out.println("Ошибка проверки: Ускорение должно быть больше нуля.");
//            return false; // Возвращаем false
         // Конец if
        return true; // Если обе проверки пройдены, возвращаем true
    }

    public static double calculatePeriod(double[] params) { // Метод ВЫЧИСЛЕНИЯ: принимает массив
        double l = params[0]; // Извлекаем длину из массива
        double g = params[1]; // Извлекаем гравитацию из массива
        return 2 * Math.PI * Math.sqrt(l / g);
    }

    public static void printResult(double period) { // Метод ВЫВОДА: принимает результат
        System.out.printf("Период колебаний: %.3f", period); // Печатаем с 3 знаками после запятой
    }

    // ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ

    public static void printMenu() { // Метод печати меню
        System.out.println("МЕНЮ");
        System.out.println("1 - Выполнить расчёт");
        System.out.println("2 - Информация о программе");
        System.out.println("3 - Информация о разработчике");
        System.out.println("0 - Выход");
    }

    public static void printAboutProgram() { // Метод инфо о программе
        System.out.println("\n--- О программе ---");
        System.out.println("Программа считает период колебаний математического маятника.");
        System.out.println("Формула: T = 2π√(L/g)");
        System.out.println("Поддерживаемые планеты: Земля, Луна, Юпитер.\n");
    }

    public static void printAboutDeveloper() { // Метод инфо о разработчике
        System.out.println("\n--- О разработчике ---");
        System.out.println("Студент: Гулаков Н.А.");
        System.out.println("Группа: РИ-250911");
        System.out.println("Лабораторная работа №2\n");
    }

    public static double readPositiveDouble(String prompt) { // Безопасное чтение дробного числа
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim(); // Читаем строку и убираем пробелы по краям
            try {
                double value = Double.parseDouble(line); // Преобразуем строку в число с плавающей точкой
                if (value <= 0) {
                    System.out.println("Ошибка: число должно быть больше нуля. Попробуйте снова.\n"); // Ошибка, если <= 0
                    continue; // Начать цикл заново
                }
                return value; // Возвращаем число, если всё ок
            } catch (NumberFormatException e) { // Если не удалось преобразовать в число
                System.out.println("Ошибка: введите корректное число (например, 1.5).\n"); // Сообщение об ошибке
            }
        }
    }

    public static double readGravity() { // Метод выбора гравитации
        while (true) { // Бесконечный цикл
            System.out.print("Где считаем? (Земля / Луна / Юпитер): ");
            String object = scanner.nextLine().trim(); // Чтение строки с удалением пробелов
            switch (object) { // Выбор по названию планеты
                case "Земля":   return 9.81; // Если Земля, вернуть 9.81
                case "Луна":    return 1.62; // Если Луна, вернуть 1.62
                case "Юпитер":  return 24.79; // Если Юпитер, вернуть 24.79
                default: // Если ввели что-то другое
                    System.out.println("Ошибка: неизвестная планета. Попробуйте снова.\n"); // Сообщение об ошибке
            }
        }
    }
}