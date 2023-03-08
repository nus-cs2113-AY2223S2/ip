package grandduke.exception.event;

import grandduke.exception.GrandException;

public class MissingToException extends GrandException {
    @Override
    public String getMessage() {
        return "The Event command is missing a /to argument.";
    }
}
