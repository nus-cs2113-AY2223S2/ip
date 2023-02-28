package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

/**
 * A abstract Command class to handle user commands
 */
public abstract class Command {
    /**
     * Executes the command the user provided.
     *
     * @param taskParser The task parser that Duke use to determine what task to execute.
     * @param ui         The Ui instance. Use to display messages to users.
     * @param storage    The storage instance. Use to write data into the text file.
     */
    public abstract void execute(TaskParser taskParser, TextUi ui, Storage storage);

    /**
     * Check if the program is exiting.
     *
     * @return the exit value.
     */
    public abstract boolean isExit();
}
