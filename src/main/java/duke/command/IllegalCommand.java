package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The IllegalCommand Class that inherits from the Command class that handles IllegalCommandExceptions
 * where the user inputs are not of the correct format
 */
public class IllegalCommand extends Command {
    /**
     * Executes the IllegalCommand where it produces an Illegal Command Message to tell the user the program does not
     * understand his/her input
     *
     * @param tasks    the TaskList that is being referred to
     * @param database the Storage that is being referred to
     */
    @Override
    public void execute(TaskList tasks, Storage database) {
        Ui.illegalCommandMessage();
    }
}
