package practicum_4;

public class example_1 {
    public static void main(String[] args) {
        System.out.println(" Создаем правильный датчик ");
        TemperatureSensor sensor1 = new TemperatureSensor("Термометр-1", "Кухня", 15.0, 30.0);
        System.out.println(sensor1.getDescription());

        System.out.println("\n=== Обновляем температуру (в пределах нормы) ===");
        sensor1.updateTemperature(22.5);
        System.out.println(sensor1.getDescription());

        System.out.println("\n Пытаемся установить температуру вне диапазона");
        try {
            sensor1.updateTemperature(50.0); // Это вызовет ошибку
        } catch (IllegalArgumentException e) {
            // Ловим ошибку и печатаем сообщение
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n Пытаемся создать датчик с пустым названием ");
        try {
            TemperatureSensor sensor2 = new TemperatureSensor("", "Спальня", 10, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nПытаемся создать датчик с неверным диапазоном");
        try {
            TemperatureSensor sensor3 = new TemperatureSensor("Датчик-3", "Зал", 40, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
    // Класс датчика температуры
     class TemperatureSensor {

        // 1. Закрытые поля (инкапсуляция)
        private String name;
        private String room;
        private double currentTemperature;
        private double minTemp;
        private double maxTemp;

        // Конструктор для создания датчика
    public TemperatureSensor(String name, String room, double minTemp, double maxTemp) {
     // Пункт 3: Проверка на пустоту и неверный диапазон
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название датчика не может быть пустым");
        }
        if (room == null || room.trim().isEmpty()) {
            throw new IllegalArgumentException("Помещение не может быть пустым");
        }
        if (minTemp > maxTemp) {
            throw new IllegalArgumentException("Неверный диапазон: минимум больше максимума");
        }

        // Если проверки пройдены — сохраняем значения в поля
        this.name = name;
        this.room = room;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;

            // По умолчанию установим текущую температуру на минимум
            this.currentTemperature = minTemp;
        }

        // Пункт 1: Обновление показания
    public void updateTemperature(double newTemperature) {
            // Пункт 4 и 5: Проверка диапазона (границы считаются допустимыми)
        if (newTemperature < minTemp || newTemperature > maxTemp) {
            throw new IllegalArgumentException("Температура вне диапазона: " + newTemperature +
                    " (допустимо от " + minTemp + " до " + maxTemp + ")");
            }
        // Если температура подходит, обновляем поле
        this.currentTemperature = newTemperature;
        }

        // Пункт 2: Получение описания датчика
    public String getDescription() {
        return "Датчик: " + name +
                    " | Помещение: " + room +
                    " | Текущая температура: " + currentTemperature + "°C" +
                    " | Допустимый диапазон: [" + minTemp + ", " + maxTemp + "]";
        }


    }

