package parser;

import duke.DukeException;

public class InvalidCommandException extends DukeException {
    /**
     * Exeception raised when there is an invalid command keyword
     * input by user.
     * @param message
     * @param err
     */
    public InvalidCommandException(String message, Throwable err) {
        super(message, err);
    }
}