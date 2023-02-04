public class InvalidCommandException extends DukeException {
    private static final String exceptionMessage = "I'm sorry, but I don't know what that means";

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException() {
        super(exceptionMessage);
    }
}
