package practicum_7.model;

public abstract class CelestialBody {
    private String name;

    public CelestialBody(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getGravity();
}
