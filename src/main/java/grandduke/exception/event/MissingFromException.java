package grandduke.exception.event;

import grandduke.exception.GrandException;

public class MissingFromException extends GrandException {
    @Override
    public String getMessage() {
        return "The Event command is missing a /from argument.";
    }
}
