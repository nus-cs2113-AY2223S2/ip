package duke;

/**
 * Exception happening specially in <code>Duke</code>.
 */
public class DukeException extends Exception {
    
    /**
     * Constructor for excetion <code>DukeException</code>. It prefixes 
     * input error message with "😣OOPS!!! ".
     * @param message Error message of <code>DukeException</code>
     */
    public DukeException(String message) {
        super("😣OOPS!!! " + message);
    }
}
