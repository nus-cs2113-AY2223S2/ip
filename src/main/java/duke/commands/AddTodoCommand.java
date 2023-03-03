package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

/**
 * The AddTodoCommand class represents a command to add a todo to the task list.
 */
public class AddTodoCommand implements Command {
    private String arguments;
    public static final String COMMAND_WORD = "todo";

    /**
     * Constructor for the AddTodoCommand class.
     *
     * @param arguments The arguments to be parsed to create the new Todo task.
     */
    public AddTodoCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the AddTodoCommand by parsing the arguments to create a new Todo task and adding it to the TaskList.
     * If the arguments are invalid or incomplete, an error message is displayed.
     *
     * @param tasks   The TaskList object containing the list of tasks.
     * @param ui      The TextUi object to handle user input and output.
     * @param storage The Storage object to handle reading and writing of task data to a file.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        if (!arguments.isBlank()) {
            Task task = new Todo(arguments);
            tasks.addTask(task);
            ui.showNewTaskMessage(tasks, task);
        } else {
            ui.showPromptEmptyErrorMessage(COMMAND_WORD);
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
