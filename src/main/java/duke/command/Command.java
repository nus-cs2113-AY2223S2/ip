package duke.command;

import duke.exception.InvalidTaskException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public abstract class Command {
    public void processCommand(TaskList tasksList, String line) throws InvalidTaskException {

    }
}
