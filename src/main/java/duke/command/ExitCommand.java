package duke.command;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents an exit command and change shouldExit to true to represent the end of the application
 */
public class ExitCommand extends Command {
    public ExitCommand(String userInput) {
        super(userInput);
    }

    /**
     * Print the exit message
     *
     * @param taskList an taskList
     * @param ui       an Ui object
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printExitLine();
    }

    /**
     * Change shouldExit as true
     *
     * @return true a boolean value to represent the end of the application
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
