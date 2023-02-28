package parser;

import duke.DukeException;

public class EmptyCommandException extends DukeException {
    /**
     * Custom exception for empty message or message with only blank spaces
     * being passed into console.
     * @param message Error message to raise
     */
    public EmptyCommandException(String message, Throwable err) {
        super(message, err);
    }
}
