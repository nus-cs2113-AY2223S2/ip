package duke.command;

import duke.exception.InvalidTaskException;
import duke.tasks.Task;

import java.util.ArrayList;

public abstract class Command {
    public void processCommand(ArrayList<Task> tasksList, String line) throws InvalidTaskException {

    }
}
