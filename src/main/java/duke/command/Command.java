package duke.command;

import duke.tasklist.TaskList;

public abstract class Command {
    protected TaskList taskList;
    protected int index = -1;

    public Command() {}

    public Command(int index) {
        this.index = index;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes.");
    }
}
