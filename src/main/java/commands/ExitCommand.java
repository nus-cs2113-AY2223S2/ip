package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

/**
 * Handles the exit command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    private boolean isExit;

    /**
     * A constructor that sets the isExit value to false.
     */
    public ExitCommand() {
        this.isExit = false;
    }

    /**
     * Setter to set the isExit value.
     *
     * @param exit the exit value to set.
     */
    public void setExit(boolean exit) {
        isExit = exit;
    }

    /**
     * Method to return the isExit value.
     *
     * @return the isExit value.
     */
    @Override
    public boolean isExit() {
        return isExit;
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
        setExit(true);
    }
}
