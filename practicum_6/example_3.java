package practicum_6;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

// Абстрактный базовый класс, задающий общий шаблон для всех небесных тел
abstract class CelestialBody {
    private String name; // Инкапсулированное поле для названия

    // Конструктор для инициализации названия тела
    public CelestialBody(String name) {
        this.name = name;
    }

    // Геттер для получения названия
    public String getName() {
        return name;
    }

    // Абстрактный метод: обязывает наследников реализовать свой расчет гравитации
    public abstract double getGravity();
}

// Производный класс: описывает планету
class Planet extends CelestialBody {
    private double gravity; // Инкапсулированное поле ускорения свободного падения

    // Конструктор с валидацией: проверяет, что гравитация больше нуля
    public Planet(String name, double gravity) {
        super(name);
        if (gravity <= 0) {
            throw new IllegalArgumentException("Ускорение должно быть > 0");
        }
        this.gravity = gravity;
    }

    // Реализация абстрактного метода: возвращает сохраненное значение
    @Override
    public double getGravity() {
        return gravity;
    }

    // Переопределение: формирует читаемую строку с данными планеты
    @Override
    public String toString() {
        return "Планета: " + getName() + ", g = " + gravity;
    }

    // Переопределение: сравнивает планеты по названию и значению гравитации
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Double.compare(planet.gravity, gravity) == 0 && Objects.equals(getName(), planet.getName());
    }

    // Переопределение: генерирует хеш-код на основе названия и гравитации
    @Override
    public int hashCode() {
        return Objects.hash(getName(), gravity);
    }
}

// Производный класс: описывает спутник планеты
class Moon extends CelestialBody {
    private double gravity;      // Ускорение свободного падения
    private String parentPlanet; // Дополнительное поле: вокруг какой планеты вращается

    // Конструктор с валидацией: проверяет гравитацию и сохраняет все поля
    public Moon(String name, double gravity, String parentPlanet) {
        super(name);
        if (gravity <= 0) {
            throw new IllegalArgumentException("Ускорение должно быть > 0");
        }
        this.gravity = gravity;
        this.parentPlanet = parentPlanet;
    }

    // Реализация абстрактного метода: возвращает значение гравитации
    @Override
    public double getGravity() {
        return gravity;
    }

    // Переопределение: выводит данные спутника, включая родительскую планету
    @Override
    public String toString() {
        return "Спутник: " + getName() + " (планеты " + parentPlanet + "), g = " + gravity;
    }

    // Переопределение: сравнивает спутники по всем трем полям
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return Double.compare(moon.gravity, gravity) == 0 &&
                Objects.equals(getName(), moon.getName()) &&
                Objects.equals(parentPlanet, moon.parentPlanet);
    }

    // Переопределение: генерирует хеш-код на основе всех трех полей
    @Override
    public int hashCode() {
        return Objects.hash(getName(), gravity, parentPlanet);
    }
}

// Класс-сущность: представляет сам физический объект (маятник)
// Теперь Pendulum — это record.
// implements Comparable<Pendulum> позволяет нам сортировать маятники (например, по длине)
record Pendulum(double length) implements Comparable<Pendulum> {

    // Компактный конструктор — нужен для проверки (валидации) данных
    public Pendulum {
        if (length <= 0) {
            throw new IllegalArgumentException("Длина должна быть больше нуля");
        }
    }

    // Метод для расчёта остался прежним
    public double calculatePeriod(CelestialBody body) {
        return 2 * Math.PI * Math.sqrt(length / body.getGravity());
    }

    // Реализация интерфейса Comparable (встроенная сортировка)
    @Override
    public int compareTo(Pendulum other) {
        // Сравниваем маятники по длине нити
        return Double.compare(this.length, other.length);
    }
}
// Главный класс с демонстрацией
public class example_3 {


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // --- 7. Массив родительского типа ---
        CelestialBody[] bodies = Arrays.stream(SolarSystemBody.values())
                .map(SolarSystemBody::toCelestialBody)
                .toArray(CelestialBody[]::new);

        System.out.println("--- Демонстрация ООП ---");

        // --- 8. Обработка массива одним циклом с вызовом полиморфного метода ---
        System.out.println("1. Список небесных тел и расчет периода для нити 1м:");
        for (CelestialBody body : bodies) {
            // Здесь вызывается полиморфный метод getGravity() и toString()
            double period = 2 * Math.PI * Math.sqrt(1.0 / body.getGravity());
            System.out.printf("%s -> Период: %.3f сек%n", body.toString(), period);
        }

        // --- 9. Демонстрация equals и hashCode ---
        System.out.println("\n2. Проверка equals и hashCode:");
        Planet earth1 = new Planet("Земля", 9.81);
        Planet earth2 = new Planet("Земля", 9.81);
        Moon moon = new Moon("Луна", 1.62, "Земля");

        System.out.println("earth1.equals(earth2): " + earth1.equals(earth2)); // true
        System.out.println("hashCode earth1 == earth2: " + (earth1.hashCode() == earth2.hashCode())); // true
        System.out.println("earth1.equals(moon): " + earth1.equals(moon)); // false

        // --- Запуск интерактивной части (старая логика, но с использованием классов) ---
        System.out.println("\n--- Интерактивный расчет ---");
        runInteractiveMode();


    // --- 10. Демонстрация record и встроенной сортировки ---
        System.out.println("\n3. Сортировка маятников (record + Comparable):");
        List<Pendulum> pendulums = new java.util.ArrayList<>();
        pendulums.add(new Pendulum(2.5));
        pendulums.add(new Pendulum(0.4));
        pendulums.add(new Pendulum(1.2));

    // Встроенная сортировка благодаря Comparable
        Collections.sort(pendulums);

        for (Pendulum p : pendulums) {
        // Обрати внимание: p.length() вместо p.getLength()
        System.out.println("Длина нити: " + p.length() + " м");
    }
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
                case 1:
                    performCalculation();
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
                    System.out.println("Ошибка: нет такого пункта.\n");
            }
        }
    }

    private static void performCalculation() {
        try {
            System.out.println("\n--- Расчёт периода ---");
            System.out.print("Введите длину нити (м): ");
            double l = Double.parseDouble(scanner.nextLine().trim());
            Pendulum pendulum = new Pendulum(l); // Создаем объект маятника

            System.out.print("Где считаем? (Земля / Луна / Юпитер): ");
            String planetName = scanner.nextLine().trim();

            SolarSystemBody found = SolarSystemBody.byDisplayName(planetName);
            if (found == null) {
                System.out.println("Ошибка: неизвестное небесное тело.");
                return;
            }
            CelestialBody selected = found.toCelestialBody();  // switch внутри

            double period = pendulum.calculatePeriod(selected);
            System.out.printf("Период колебаний на %s (%s): %.3f сек%n",
                    found.getDisplayName(),
                    found.getKind().getTitle(),   // используем доп. поле enum
                    period);


        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный формат числа.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Вспомогательные методы (без изменений)
    public static void printMenu() {
        System.out.println("МЕНЮ");
        System.out.println("1 - Выполнить расчёт");
        System.out.println("2 - Информация о программе");
        System.out.println("3 - Информация о разработчике");
        System.out.println("0 - Выход");
    }

    public static void printAboutProgram() {
        System.out.println("\n--- О программе ---");
        System.out.println("Программа считает период колебаний математического маятника.");
        System.out.println("Использует ООП подход.\n");
    }

    public static void printAboutDeveloper() {
        System.out.println("\n--- О разработчике ---");
        System.out.println("Студент: Гулаков Н.А.");
        System.out.println("Группа: РИ-250911");
        System.out.println("Лабораторная работа №3\n");
    }
}