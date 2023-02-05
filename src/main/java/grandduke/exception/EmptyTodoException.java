package grandduke.exception;

public class EmptyTodoException extends GrandException {
    @Override
    public String getMessage() {
        return "The Todo command is missing a description.";
    }
}
