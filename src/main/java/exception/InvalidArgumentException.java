package exception;

public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
