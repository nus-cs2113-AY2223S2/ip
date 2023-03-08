package grandduke.exception.deadline;

import grandduke.exception.GrandException;

public class EmptyDeadlineException extends GrandException {
    @Override
    public String getMessage() {
        return "The Deadline command is missing the entire description.";
    }
}
