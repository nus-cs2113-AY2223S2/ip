package duke.commands;

import duke.Storage;
import duke.TaskList;

public abstract class Command {
    /**
     * Returns the state if a command is ExitCommand or not.
     *
     * @return boolean state of the command.
     */
    public abstract boolean isExit();

    /**
     * Executes a specific action based on Command type.
     *
     * @param tasks   the current state of TaskList.
     * @param storage the current state of Storage.
     */
    public abstract void execute(TaskList tasks, Storage storage);
}