package duke.command;

import duke.task.TaskList;

public abstract class Command {
    public abstract void run(TaskList taskList);

    public boolean isExit() {
        return false;
    }
}
