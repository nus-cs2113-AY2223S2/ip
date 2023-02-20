package duke.command;

import duke.task.TaskList;

/** Represents an executable command */
public abstract class Command {

    protected TaskList taskList;

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command and returns the result
     */
    public abstract CommandResult execute();
}