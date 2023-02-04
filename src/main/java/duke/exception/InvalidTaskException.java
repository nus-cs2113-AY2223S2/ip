package duke.exception;

public class InvalidTaskException extends Exception {
    public String getMessage() {
        return "Invalid Task Command, PLease enter Valid Command and Format";
    }
}
