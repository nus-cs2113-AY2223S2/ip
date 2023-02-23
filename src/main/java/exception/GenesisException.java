package exception;

/**
 * An exception class that represents an error specific to the Genesis chatbot application.
 * This exception is used to indicate that an operation or command cannot be executed due to some error
 * or invalid input by the user.
 */
public class GenesisException extends Exception {
    public GenesisException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
