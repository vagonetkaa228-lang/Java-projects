package practicum_7.exception;

// Наследуемся от Exception — это делает исключение ПРОВЕРЯЕМЫМ
public class BodyNotFoundException extends Exception {
    public BodyNotFoundException(String message) {
        super(message);
    }
}