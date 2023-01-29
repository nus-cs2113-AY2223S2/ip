package task;

import duke.DukeException;

public class EmptyTaskListException extends DukeException {
    /**
     * Exception for when task list is empty and user request to print
     * task list.
     * @param message Error message to raise
     */
    public EmptyTaskListException(String message, Throwable err) {
        super(message, err);
    }
}