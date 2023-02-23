package duke.command;

import duke.exception.InvalidTaskException;
import duke.tasks.TaskList;


/**
 * each command is represented one command object
 */
public abstract class Command {
    public void processCommand(TaskList tasksList, String line) throws InvalidTaskException {
    }
}
