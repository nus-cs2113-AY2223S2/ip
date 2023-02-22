package dude.exception;

public class EmptyInputException extends DudeException {
    @Override
    public String getMessage() {
        return "Please input a valid command!";
    }
}

