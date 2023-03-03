package duke.commands;
/**
 * The FindByDateCommand class represents a command to display all task containing the date.
 */

import java.time.LocalDateTime;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class FindByDateCommand implements Command {
    public static final String COMMAND_WORD = "find-date";
    Parser parser = new Parser();
    private String arguments;

    /**
     * Constructor for the FindByDateCommand class.
     *
     * @param arguments to be parsed as a date to be searched.
     */
    public FindByDateCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the FindByDateCommand by parsing the arguments as a date to be searched. Display the list of tasks containing the date
     * If the arguments are invalid or incomplete, an error message is displayed.
     *
     * @param tasks The TaskList object containing the list of tasks.
     * @param ui The TextUi object to handle user input and output.
     * @param storage The Storage object to handle reading and writing of task data to a file.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        LocalDateTime date = parser.parseDate(arguments);
        if (date != null) {
            ui.showTaskListByDate(tasks.getTasks(), date);
        }
    }

    /**
     * Returns a boolean value indicating if the command is an exit command.
     *
     * @return false since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
