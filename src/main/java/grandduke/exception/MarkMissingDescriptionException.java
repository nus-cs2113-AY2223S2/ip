package grandduke.exception;

public class MarkMissingDescriptionException extends GrandException {
    @Override
    public String getMessage() {
        return "The Mark/Unmark command is missing an index.";
    }
}
