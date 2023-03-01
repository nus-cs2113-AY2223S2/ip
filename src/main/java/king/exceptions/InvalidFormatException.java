package king.exceptions;

/**
 * An exception representing an input string which is
 * given in the wrong format and missing required props of /by or /from or /to
 * 
 */
public class InvalidFormatException extends KingException {
    private static final String MESSAGE = "Such horrendous formatting! Please tryeth again.";

    public InvalidFormatException(String... missing) {
        super("Such horrendous formatting! Please includeth " + String.join(" and ", missing));
    }

    public InvalidFormatException() {
        super(MESSAGE);
    }
}
