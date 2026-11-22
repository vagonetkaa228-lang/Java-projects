package practicum_7.model;

import java.util.Objects;

public class Moon extends CelestialBody {
    private double gravity;
    private String parentPlanet;

    public Moon(String name, double gravity, String parentPlanet) {
        super(name);
        if (gravity <= 0) {
            throw new IllegalArgumentException("Ускорение должно быть > 0");
        }
        this.gravity = gravity;
        this.parentPlanet = parentPlanet;
    }

    @Override
    public double getGravity() {
        return gravity;
    }

    @Override
    public String toString() {
        return "Спутник: " + getName() + " (планеты " + parentPlanet + "), g = " + gravity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return Double.compare(moon.gravity, gravity) == 0 &&
                Objects.equals(getName(), moon.getName()) &&
                Objects.equals(parentPlanet, moon.parentPlanet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), gravity, parentPlanet);
    }
}