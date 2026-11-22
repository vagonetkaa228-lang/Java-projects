package practicum_7.model;

import java.util.Objects;

public class Planet extends CelestialBody {
    private double gravity;

    public Planet(String name, double gravity) {
        super(name);
        if (gravity <= 0) {
            throw new IllegalArgumentException("Ускорение должно быть > 0");
        }
        this.gravity = gravity;
    }

    @Override
    public double getGravity() {
        return gravity;
    }

    @Override
    public String toString() {
        return "Планета: " + getName() + ", g = " + gravity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Double.compare(planet.gravity, gravity) == 0 && Objects.equals(getName(), planet.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), gravity);
    }
}