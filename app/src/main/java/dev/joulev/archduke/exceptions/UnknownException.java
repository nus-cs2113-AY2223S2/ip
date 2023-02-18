package dev.joulev.archduke.exceptions;

/**
 * This class represents any exceptions that are likely caused by an Archduke
 * bug.
 */
public class UnknownException extends ArchdukeException {
    /**
     * Constructs a new {@link UnknownException} exception. We simply pass the error
     * message to the superclass.
     * 
     * @param message The error message
     */
    public UnknownException(String message) {
        super(message);
    }

    public String getErrorString() {
        return "An unknown error has occurred. It is likely a bug. Please report it to @joulev on GitHub with the following message: \""
                + getMessage() + "\".";
    }
}
