package Exceptions;

// indicate there is a valid json value
// but there are still some value behind it
public class RootNotSingularException extends RuntimeException {
    private String message ;

    public RootNotSingularException() {
        super();
    }

    public RootNotSingularException(String message) {
        super(message);
    }
}
