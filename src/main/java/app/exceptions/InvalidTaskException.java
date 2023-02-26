package app.exceptions;

/**
 * Class to handle the case of invalid Task indexes input by user.
 */
public class InvalidTaskException extends DukeException{

    /**
     * Constructor to output an error message if the Task index is invalid
     * and out of bounds.
     */
    public InvalidTaskException() {
        super("ONO! Please enter a valid task index.");
    }
}
