package task;

import duke.DukeException;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message, Throwable err) {
        super(message, err);
    }
}