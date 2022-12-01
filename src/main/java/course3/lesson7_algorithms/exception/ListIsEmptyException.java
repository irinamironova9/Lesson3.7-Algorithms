package course3.lesson7_algorithms.exception;

public class ListIsEmptyException extends RuntimeException {

    public ListIsEmptyException() {
    }

    public ListIsEmptyException(String message) {
        super(message);
    }
}
