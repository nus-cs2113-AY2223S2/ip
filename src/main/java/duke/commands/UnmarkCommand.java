package duke.commands;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

/**
 * The MarkCommad class represents a command to mark a task as undone.
 */
public class UnmarkCommand implements Command {
    public static final String COMMAND_WORD = "unmark";
    private String arguments;

    /**
     * Constructor for the MarkCommand class.
     *
     * @param arguments to be parsed as a task to be marked.
     */
    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the UnmarkCommand by parsing the index of tasks to be unmarked
     * If the arguments are invalid or incomplete, an error message is displayed.
     *
     * @param tasks   The TaskList object containing the list of tasks.
     * @param ui      The TextUi object to handle user input and output.
     * @param storage The Storage object to handle reading and writing of task data to a file.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Parser parser = new Parser();
        int indexTask = parser.parseIndex(arguments);
        try {
            if (indexTask != -1) {
                Task selectedTask = tasks.getTaskByIndex(indexTask);
                selectedTask.setStatus(false);
                ui.showTaskStatusMessage(selectedTask);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showTaskIndexOutOfBoundsError();
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
