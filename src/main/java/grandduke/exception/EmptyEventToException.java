package grandduke.exception;

public class EmptyEventToException extends GrandException {
    @Override
    public String getMessage() {
        return "The Event command is missing the to date and time.";
    }
}
