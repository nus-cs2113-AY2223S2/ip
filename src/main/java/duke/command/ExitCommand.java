package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ExitCommand class that inherits from the Command class that handles the user input of "bye"
 */
public class ExitCommand extends Command {
    /**
     * Executes the ExitCommand to show a goodbye message and change the boolean isExit to true so that the while loop
     * stops and safely exits the program
     *
     * @param tasks    the TaskList that is being referred to
     * @param database the Storage that is being referred to
     */
    @Override
    public void execute(TaskList tasks, Storage database) {
        Ui.goodbyeMessage();
        isExit = true;
    }


}
