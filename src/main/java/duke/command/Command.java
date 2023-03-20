package duke.command;

import duke.DukeException;
import duke.TaskList;

public abstract class Command {
    public boolean execute (TaskList taskList) throws DukeException {
        throw new DukeException("Unknown Command");
    }
}
