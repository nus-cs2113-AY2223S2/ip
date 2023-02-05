package grandduke.exception;

public class EmptyDeadlineException extends GrandException {
    @Override
    public String getMessage() {
        return "The Deadline command is missing the entire description.";
    }
}
