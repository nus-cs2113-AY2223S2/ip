package tusky.tasks;

public class EmptyDescriptionException extends Exception {
    EmptyDescriptionException (String message) {
        super(message);
    }
}
