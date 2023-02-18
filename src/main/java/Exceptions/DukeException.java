package Exceptions;

/**
 * Base Exception Class with custom formatting of error message 
 */
public class DukeException extends Exception {
    /**
     * Constructor for printing error messages
     * @param errorMessage error message
     */
    public DukeException(String errorMessage) {
        super("\t"+errorMessage);
    }

    /**
     * Constructor for printing error origins
     * @param errorMessage error message
     * @param err Error
     */
    public DukeException(String errorMessage, Throwable err) {
        super("\t"+errorMessage, err);
    }
}
