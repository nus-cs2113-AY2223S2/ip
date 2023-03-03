package duke.commands;

import duke.TaskList;

/**
 * Represents a Command.
 * Adapted from nus-cs2113-AY2223S2/personbook
 */

public class Command {

    protected TaskList taskList;
    public int targetIndex = -1;

    /**
     * Supplies the task list that the command will operate on
     * @param taskList
     */
    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }
    public Command(int targetIndex) {
        this.targetIndex = targetIndex;
    }
    protected Command() {
    }

    /**
     * Executes command and return results
     * Ensures that execute() is not implemented by Command class
     */

    public CommandResult execute() {
        throw new UnsupportedOperationException("method to be implemented by child classes");
    };

}
