package duke.command;

import duke.exception.InvalidTaskException;
import duke.tasks.Task;

public abstract class Command {
    public void processCommand(Task[] tasksList, String line) throws InvalidTaskException {

    }
}
