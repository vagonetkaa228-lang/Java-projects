package practicum_7.exception;

// Наследуемся от RuntimeException — это делает исключение НЕПРОВЕРЯЕМЫМ
public class InvalidPhysicsStateException extends RuntimeException {
    public InvalidPhysicsStateException(String message) {
        super(message);
    }
}