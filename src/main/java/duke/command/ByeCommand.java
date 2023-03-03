package duke.command;

import duke.TaskList;
import duke.UI;
import duke.Storage;

/**
 * Command class for the user to exit the program.
 */
public class ByeCommand extends Command {
    public ByeCommand() {

    }


    /**
     * Prints goodbye message before user exits the program.
     * @param ui Prints goodbye message.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        ui.printBye();
        ui.printSeparator();
    }
}
