package task;

import duke.DukeException;

public class EmptyTaskListException extends DukeException {
    /**
     * Custom exception for empty message or message with only blank spaces
     * being passed into console.
     * @param message Error message to raise
     */
    public EmptyTaskListException(String message, Throwable err) {
        super(message, err);
    }
}