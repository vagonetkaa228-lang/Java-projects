package practicum_7.model;

import practicum_7.exception.InvalidPhysicsStateException; // Импортируем нашу ошибку

public record Pendulum(double length) implements Comparable<Pendulum> {

    public Pendulum {
        if (length <= 0) {
            // Выбрасываем непроверяемое исключение
            throw new InvalidPhysicsStateException("Длина нити должна быть строго больше нуля! Вы ввели: " + length);
        }
    }

    public double calculatePeriod(CelestialBody body) {
        return 2 * Math.PI * Math.sqrt(length / body.getGravity());
    }

    @Override
    public int compareTo(Pendulum other) {
        return Double.compare(this.length, other.length);
    }
}