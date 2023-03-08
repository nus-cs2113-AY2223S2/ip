package grandduke.exception.mark;

import grandduke.exception.GrandException;

public class MarkMissingDescriptionException extends GrandException {
    @Override
    public String getMessage() {
        return "The Mark/Unmark command is missing an index.";
    }
}
