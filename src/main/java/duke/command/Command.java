package duke.command;

import duke.Storage;
import duke.TaskList;

public abstract class Command {

    public boolean isExit;

    public Command() {
        isExit = false;
    }

    public abstract void execute(TaskList tasks, Storage database);

}
