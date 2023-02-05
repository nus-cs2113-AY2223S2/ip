package grandduke.exception;

public class EmptyDeadlineDateException extends GrandException {
    @Override
    public String getMessage() {
        return "The Deadline command is missing a date.";
    }
}
