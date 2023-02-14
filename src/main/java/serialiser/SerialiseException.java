package serialiser;

import duke.DukeException;

public class SerialiseException extends DukeException{
    public SerialiseException(String message, Throwable err) {
        super(message,err);
    }
}