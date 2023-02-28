package task;

import duke.DukeException;

public class EmptyDescriptionException extends DukeException {
    /**
     * Exception when user inputs empty description for task
     * @param message
     * @param err
     */
    public EmptyDescriptionException(String message, Throwable err) {
        super(message, err);
    }
}