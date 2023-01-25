package Exceptions;

public class UnknownInputException extends DukeException {
    private static String errorMessage = "Apologies, I do not understand your request :-(";

    public UnknownInputException() {
        super(errorMessage);
    }

    public UnknownInputException(Throwable err) {
        super(errorMessage, err);
    }
}
