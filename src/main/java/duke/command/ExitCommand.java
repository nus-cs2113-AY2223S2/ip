package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Exit Command class that allows user to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Prints exit message.
     * Sets the exit status of the Command to be true.
     *
     * @param ui Prints exit message to user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        this.setExit();
    }

}
