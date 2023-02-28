package commands;

import storage.Storage;
import task.TaskParser;
import task.Todo;
import ui.TextUi;

/**
 * Handles the todo command.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private String description;

    /**
     * A constructor that accepts the description specified by the user.
     *
     * @param description The description of the deadline.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command the user provided.
     *
     * @param taskParser The task parser that Duke use to determine what task to execute.
     * @param ui         The Ui instance. Use to display messages to users.
     * @param storage    The storage instance. Use to write data into the text file.
     */
    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        Todo todo = taskParser.createTodoTask(description);
        taskParser.addAndPrintTask(todo, ui, storage);

    }

    /**
     * Check if the program is exiting.
     *
     * @return the exit value.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
