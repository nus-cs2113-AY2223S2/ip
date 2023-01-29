package task;

import duke.DukeException;

public class TaskMarkException extends DukeException{
    public TaskMarkException(String message, Throwable err) {
        super(message, err);
    }
}
