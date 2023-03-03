package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Base class for all types of Commands.
 */
public class Command {

    public Command() {

    }

    /**
     * Executes the user's command
     * @param tasks The task list that the user modifies
     * @param storage Updates when task list is modified
     * @param ui Prints error messages if command cannot be executed.
     */
    public void execute(TaskList tasks, Storage storage, UI ui) {

    }
}
