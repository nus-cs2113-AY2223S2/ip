package Exceptions;

public class InsufficientArgumentsException extends DukeException {
    public InsufficientArgumentsException(String command, String format) {
        super("Oops! The format of " + command + " is: " + format);
    }

    public InsufficientArgumentsException(String command, String format, Throwable err) {
        super("Oops! The format of " + command + " is: " + format, err);
    }
}