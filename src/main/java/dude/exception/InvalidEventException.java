package dude.exception;

public class InvalidEventException extends DudeException {
    @Override
    public String getMessage() {
        return "Please input a event command e.g.(event description /from start /to end)";
    }
}
