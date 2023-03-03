package duke.exception;

import duke.exception.DukeException;

public class TaskNumberException extends DukeException {
    public TaskNumberException(){
        super("OOPS!!! Please input a single valid task number.");
    }
}
