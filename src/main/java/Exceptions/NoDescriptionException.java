package Exceptions;

public class NoDescriptionException extends DukeException {
    public NoDescriptionException(String command) {
        super("Oops! The description of " + command + " cannot be empty.");
    }
}
