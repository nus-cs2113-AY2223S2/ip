package duke.exception;

/**
 * triggers when task command is invalid
 */
public class InvalidTaskException extends Exception {
    public String getMessage() {
        return "Invalid Task Command, PLease enter valid command and format";
    }
}
