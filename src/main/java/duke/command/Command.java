package duke.command;

import duke.tasklist.TaskList;

public abstract class Command {

    protected String commandType;

    public Command(String commandType) {
        this.commandType = commandType;
    }

    public abstract void execute(TaskList tasks);
}