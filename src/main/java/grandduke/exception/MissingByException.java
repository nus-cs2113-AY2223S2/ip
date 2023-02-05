package grandduke.exception;

public class MissingByException extends GrandException {
    @Override
    public String getMessage() {
        return "The Deadline command is missing a /by argument.";
    }
}
