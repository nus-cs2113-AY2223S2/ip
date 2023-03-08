package grandduke.exception;

public class UnrecognisedCommandException extends GrandException {
    @Override
    public String getMessage() {
        return "Unrecognised command has been entered. Please try again.";
    }
}
