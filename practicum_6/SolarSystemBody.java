package practicum_6;

public enum SolarSystemBody {

    // Константы: имя_для_программы(русское_имя, g, тип, родитель)
    EARTH  ("Земля",  9.81,  BodyKind.PLANET, null),
    MOON   ("Луна",   1.62,  BodyKind.MOON,   "Земля"),
    JUPITER("Юпитер", 24.79, BodyKind.PLANET, null);

    // Вложенный enum — это и есть «дополнительное поле», задающее вид тела
    public enum BodyKind {
        PLANET("Планета"),
        MOON  ("Спутник");

        private final String title;
        BodyKind(String title) { this.title = title; }
        public String getTitle() { return title; }
    }

    // Дополнительные поля у каждой константы enum
    private final String  displayName; // человекочитаемое имя
    private final double  gravity;     // g, м/с²
    private final BodyKind kind;       // планета или спутник
    private final String  parentName;  // для спутника — вокруг кого вращается

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

    // Поиск по русскому имени (заменяет старую if/else-цепочку)
    public static SolarSystemBody byDisplayName(String name) {
        for (SolarSystemBody body : values()) {
            if (body.displayName.equalsIgnoreCase(name)) return body;
        }
        return null;
    }

    // ⇩⇩⇩ ВОТ ОН — осмысленный switch по enum ⇩⇩⇩
    // По виду тела создаём нужный наследник CelestialBody.
    public CelestialBody toCelestialBody() {
        return switch (kind) {
            case PLANET -> new Planet(displayName, gravity);
            case MOON   -> new Moon(displayName, gravity, parentName);
        };
    }
}