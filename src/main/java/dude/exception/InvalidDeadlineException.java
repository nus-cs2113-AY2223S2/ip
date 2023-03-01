package dude.exception;

public class InvalidDeadlineException extends DudeException {
    @Override
    public String getMessage() {
        return "Please input a valid deadline command e.g.(deadline description /by time) ";
    }
}
