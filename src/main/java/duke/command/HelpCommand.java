package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Help Command class that shows user a list of all available Commands.
 */
public class HelpCommand extends Command {

    /**
     * Prints list of all available Commands and their abilities.
     *
     * @param ui Prints list of Commands to user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printHelpMessage();
    }

}
