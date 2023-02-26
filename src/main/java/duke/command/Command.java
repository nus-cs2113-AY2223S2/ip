package duke.command;

import duke.data.TaskList;
import duke.ui.*;
/**
 * Abstract class to manage all commands
 */
public abstract class Command {
    /**
     * Abstract method to execute all commands
     *
     * @param input
     * @param tasks
     * @param ui
     */
    public abstract void runCommand(String input, TaskList tasks, UI ui);
}
