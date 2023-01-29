package task;

import duke.DukeException;

public class TaskMarkException extends DukeException{
    /**
     * Exception thrown when task is either already marked or unmark
     * and user request to perform an action that does not change its status
     * @param message
     * @param err
     */
    public TaskMarkException(String message, Throwable err) {
        super(message, err);
    }
}
