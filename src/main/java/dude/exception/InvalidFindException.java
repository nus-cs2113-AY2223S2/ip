package dude.exception;

public class InvalidFindException extends DudeException {
    @Override
    public String getMessage() {
        return "Please input a valid string to find!";
    }
}
