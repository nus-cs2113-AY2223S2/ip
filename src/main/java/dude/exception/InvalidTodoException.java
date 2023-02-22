package dude.exception;

public class InvalidTodoException extends DudeException {
    @Override
    public String getMessage() {
        return "Please input a valid todo command e.g.(todo description)";
    }
}
