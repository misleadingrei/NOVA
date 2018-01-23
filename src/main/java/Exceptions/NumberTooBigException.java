package Exceptions;

public class NumberTooBigException extends RuntimeException {
    private String message ;

    public NumberTooBigException() {
        super();
    }

    public NumberTooBigException(String message) {
        super(message);
    }
}
