package exceptions;

public abstract class ArchdukeException extends Exception {
    public ArchdukeException(String message) {
        super(message);
    }

    public abstract String getErrorString() throws UnknownException;
}
