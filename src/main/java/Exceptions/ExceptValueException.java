package Exceptions;

public class ExceptValueException extends  RuntimeException {

    private String message ;

    public ExceptValueException() {
        super();
    }

    public ExceptValueException(String message) {
        super(message);
    }
}
