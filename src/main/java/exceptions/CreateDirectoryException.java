package exceptions;

/**
 * Exceptions related to create directory.
 */
public class CreateDirectoryException extends Exception {
    /**
     * It will print out a message when an exception regarding directory creation is hit.
     *
     * @param message The message to print.
     */
    public CreateDirectoryException(String message) {
        super(message);
    }
}
