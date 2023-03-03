package duke.exception;

public class EmptyCommandException extends Exception{
    public EmptyCommandException(String errorMessage) {
        super(errorMessage);
    }
}
