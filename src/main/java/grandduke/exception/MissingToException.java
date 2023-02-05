package grandduke.exception;

public class MissingToException extends GrandException {
    @Override
    public String getMessage() {
        return "The Event command is missing a /to argument.";
    }
}
