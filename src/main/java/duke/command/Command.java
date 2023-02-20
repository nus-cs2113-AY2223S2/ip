package duke.command;

import duke.task.TaskList;


public abstract class Command {
    protected TaskList taskList;

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract CommandResult execute();
}