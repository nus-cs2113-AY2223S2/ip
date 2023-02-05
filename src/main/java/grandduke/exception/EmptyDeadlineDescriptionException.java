package grandduke.exception;

public class EmptyDeadlineDescriptionException extends GrandException {
    @Override
    public String getMessage() {
        return "The Deadline command is missing the naming description.";
    }
}
