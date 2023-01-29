package parser;

import Duke.DukeException;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message, Throwable err) {
        super(message, err);
    }
}