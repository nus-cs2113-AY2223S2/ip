package grandduke.exception.todo;

import grandduke.exception.GrandException;

public class EmptyTodoException extends GrandException {
    @Override
    public String getMessage() {
        return "The Todo command is missing a description.";
    }
}
