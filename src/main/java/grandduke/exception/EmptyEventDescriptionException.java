package grandduke.exception;

public class EmptyEventDescriptionException extends GrandException {
    @Override
    public String getMessage() {
        return "The Event is missing the name description";
    }
}
