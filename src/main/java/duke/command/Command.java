package duke.command;

import duke.util.TaskList;

public abstract class Command {

    public abstract void run (TaskList taskList);

    public boolean toExit() {
        return false;
    }
}
