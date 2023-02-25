package exception;

/**
 * This exception is thrown to indicate that an invalid argument has been passed
 * to a method or function.
 */
public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
