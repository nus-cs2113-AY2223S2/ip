package Exceptions;

public class UnknownInputException extends DukeException {
    public UnknownInputException() {
        super("Apologies, I do not understand your request :-(");
    }
}
