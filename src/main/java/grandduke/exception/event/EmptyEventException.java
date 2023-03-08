package grandduke.exception.event;

import grandduke.exception.GrandException;

public class EmptyEventException extends GrandException {
    @Override
    public String getMessage() {
        return "The Event command is a missing the entire description.";
    }
}
