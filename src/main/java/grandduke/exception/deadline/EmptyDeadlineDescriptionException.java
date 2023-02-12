package grandduke.exception.deadline;

import grandduke.exception.GrandException;

public class EmptyDeadlineDescriptionException extends GrandException {
    @Override
    public String getMessage() {
        return "The Deadline command is missing the naming description.";
    }
}
