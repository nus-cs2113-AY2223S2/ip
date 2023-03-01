package Exceptions;

/**
 * Called when Duke is unable to parse user input at all
 */
public class UnknownInputException extends DukeException {
    /**
     * Constructor for printing unknown input error message
     */
    public UnknownInputException() {
        super("Apologies, I do not understand your request :-(");
    }
}
