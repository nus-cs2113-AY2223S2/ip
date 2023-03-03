package duke.commands;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

/**
 * The AddTodoCommand class represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    public static final String COMMAND_WORD = "delete";
    private String arguments;

    /**
     * Constructor for the AddEventCommand class.
     *
     * @param arguments The arguments to be parsed as a task to be deleted.
     */
    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the DeleteCommand by parsing the index of task to be deleted from the TaskList.
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
                Task deletedTask = tasks.deleteTask(indexTask);
                ;
                ui.showDeleteTaskMessage(tasks, deletedTask);
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
