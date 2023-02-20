package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command generated and run when there is an error with parsing the command.
 */
public class InvalidCommand extends Command {
    private String errorMessage;
    private String command;

    /**
     * Constructs a command that will display an error message and the relevant help message.
     *
     * @param errorMessage Description of the error.
     * @param command Type of command that caused the error, to identify which help message to display.
     */
    public InvalidCommand(String errorMessage, String command) {
        this.errorMessage = errorMessage;
        this.command = command;
    }

    /**
     * Displays the error and help messages provided in the constructor.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void run(TaskList taskList) {
        Ui.printError(errorMessage, command);
    }
}
