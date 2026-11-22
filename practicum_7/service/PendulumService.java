package practicum_7.service;

import practicum_7.exception.BodyNotFoundException;
import practicum_7.model.CelestialBody;
import practicum_7.model.SolarSystemBody;

public class PendulumService {

    // Обрати внимание: мы добавили throws BodyNotFoundException
    public CelestialBody findBody(String name) throws BodyNotFoundException {
        SolarSystemBody found = SolarSystemBody.byDisplayName(name);

        if (found == null) {
            // Выбрасываем наше проверяемое исключение с понятным текстом
            throw new BodyNotFoundException("Небесное тело '" + name + "' не найдено в справочнике!");
        }

        return found.toCelestialBody();
    }
}