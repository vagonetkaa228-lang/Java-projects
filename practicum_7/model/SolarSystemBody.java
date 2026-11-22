package practicum_7.model;

public enum SolarSystemBody {

    EARTH  ("Земля",  9.81,  BodyKind.PLANET, null),
    MOON   ("Луна",   1.62,  BodyKind.MOON,   "Земля"),
    JUPITER("Юпитер", 24.79, BodyKind.PLANET, null);

    public enum BodyKind {
        PLANET("Планета"),
        MOON  ("Спутник");

        private final String title;
        BodyKind(String title) { this.title = title; }
        public String getTitle() { return title; }
    }

    private final String  displayName;
    private final double  gravity;
    private final BodyKind kind;
    private final String  parentName;

    SolarSystemBody(String displayName, double gravity, BodyKind kind, String parentName) {
        this.displayName = displayName;
        this.gravity     = gravity;
        this.kind        = kind;
        this.parentName  = parentName;
    }

    public String   getDisplayName() { return displayName; }
    public double   getGravity()     { return gravity;     }
    public BodyKind getKind()        { return kind;        }
    public String   getParentName()  { return parentName;  }

    public static SolarSystemBody byDisplayName(String name) {
        for (SolarSystemBody body : values()) {
            if (body.displayName.equalsIgnoreCase(name)) return body;
        }
        return null; // Позже мы заменим это на выброс исключения!
    }

    public CelestialBody toCelestialBody() {
        return switch (kind) {
            case PLANET -> new Planet(displayName, gravity);
            case MOON   -> new Moon(displayName, gravity, parentName);
        };
    }
}