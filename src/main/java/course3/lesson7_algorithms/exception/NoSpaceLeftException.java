package course3.lesson7_algorithms.exception;

public class NoSpaceLeftException extends RuntimeException {

    public NoSpaceLeftException() {
    }

    public NoSpaceLeftException(String message) {
        super(message);
    }
}
