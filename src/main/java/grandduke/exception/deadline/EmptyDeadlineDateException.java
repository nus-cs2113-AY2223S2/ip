package grandduke.exception.deadline;

import grandduke.exception.GrandException;

public class EmptyDeadlineDateException extends GrandException {
    @Override
    public String getMessage() {
        return "The Deadline command is missing a date.";
    }
}
