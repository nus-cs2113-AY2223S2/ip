package task;

import duke.DukeException;

public class TaskIndexOutOfRangeException extends DukeException {
    /**
     * Exception when searching for given index and index is out of range.
     * @param message
     * @param err
     */
    public TaskIndexOutOfRangeException(String message, Throwable err) {
        super(message, err);
    }
}
