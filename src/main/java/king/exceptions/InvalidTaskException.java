package king.exceptions;

/**
 * An exception representing an input string which cannot
 * be parsed into a valid Task
 */
public class InvalidTaskException extends KingException {
    public InvalidTaskException(String task) {
        super("Thy description of a " + task + " shalt not be empty.");
    }
}
