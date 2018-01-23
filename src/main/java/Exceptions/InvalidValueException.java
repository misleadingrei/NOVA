package Exceptions;

public class InvalidValueException extends RuntimeException {
    private String message ;

    public InvalidValueException() {
        super();
    }

    public InvalidValueException(String message) {
        super(message);
    }
}
