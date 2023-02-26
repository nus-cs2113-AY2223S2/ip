package duke.command;

import duke.data.TaskList;

public abstract class Command {
    protected TaskList taskList;

    public Command() {}

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes.");
    }
}