package dev.joulev.archduke.exceptions;

/**
 * This class represents any exceptions that are not due to program bugs and
 * user input, but are still known to the program and can be handled. Example:
 * failure in accessing the file system.
 */
public class OtherException extends ArchdukeException {
    /**
     * Constructs a new {@link OtherException} exception. We simply pass the error
     * message to the superclass.
     * 
     * @param message The error message
     */
    public OtherException(String message) {
        super(message);
    }

    public String getErrorString() {
        return getMessage();
    }
}
