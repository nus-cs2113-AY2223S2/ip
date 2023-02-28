package duke.commands;

import duke.file.TaskList;
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
    public abstract void execute(String input, TaskList tasks, UI ui);
}