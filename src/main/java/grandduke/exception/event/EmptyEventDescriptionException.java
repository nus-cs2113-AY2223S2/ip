package grandduke.exception.event;

import grandduke.exception.GrandException;

public class EmptyEventDescriptionException extends GrandException {
    @Override
    public String getMessage() {
        return "The Event is missing the name description";
    }
}
