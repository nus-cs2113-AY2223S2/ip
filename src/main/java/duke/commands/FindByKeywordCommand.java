package duke.commands;
/**
 * The FindByKeywordCommand class represents a command to display all task containing the keyword.
 */

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class FindByKeywordCommand implements Command {
    public static final String COMMAND_WORD = "find";
    private String arguments;

    /**
     * Constructor for the FindByDateCommand class.
     *
     * @param arguments to be parsed as string to be searched.
     */
    public FindByKeywordCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the FindByKeywordCommand by parsing the arguments as string to be searched. Display the list of tasks containing the keyword
     * If the arguments are invalid or incomplete, an error message is displayed.
     *
     * @param tasks The TaskList object containing the list of tasks.
     * @param ui The TextUi object to handle user input and output.
     * @param storage The Storage object to handle reading and writing of task data to a file.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showTaskListByKeyword(tasks.getTasks(), arguments);
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
