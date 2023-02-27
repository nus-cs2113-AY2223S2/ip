package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

/**
 * Handles the list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Executes the command the user provided.
     *
     * @param taskParser The task parser that Duke use to determine what task to execute.
     * @param ui         The Ui instance. Use to display messages to users.
     * @param storage    The storage instance. Use to write data into the text file.
     */
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        taskParser.listTasks(ui);

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
