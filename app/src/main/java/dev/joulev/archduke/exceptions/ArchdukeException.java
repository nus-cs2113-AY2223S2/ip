package dev.joulev.archduke.exceptions;

/**
 * An abstract class that represents all exceptions that can be thrown by
 * {@link Archduke}. The program will catch this exception and print the error
 * accordingly, without having to check the type of each individual exception.
 */
public abstract class ArchdukeException extends Exception {
    public ArchdukeException(String message) {
        super(message);
    }

    /**
     * Gets a user-friendly error string that will be printed to the terminal.
     * 
     * @return The error string
     * @throws UnknownException If any exception is unknown
     */
    public abstract String getErrorString() throws UnknownException;
}
