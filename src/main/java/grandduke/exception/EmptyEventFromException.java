package grandduke.exception;

public class EmptyEventFromException extends GrandException {
    @Override
    public String getMessage() {
        return "The Event command is missing the from date and time.";
    }
}
