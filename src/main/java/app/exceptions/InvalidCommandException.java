package app.exceptions;

/**
 * Class to handle the case of invalid commands entered by user.
 */
public class InvalidCommandException extends DukeException{

    /**
     * Constructor to output an error message if the command type is invalid
     * and not recognised by Duke.
     */
    public InvalidCommandException() {
        super("ONO! Please enter a valid command.");
    }
}
