package app.exceptions;

/**
 * Class to handle all possible Exceptions thrown by Duke
 */
public class DukeException extends Exception{

    /**
     * Constructor to output any Exception's error message.
     * @param errorMessage The error message to be printed.
     */
    public DukeException(String errorMessage){
        super(errorMessage);
    }
}
