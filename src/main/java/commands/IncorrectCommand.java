package commands;

import common.Messages;
import storage.Storage;
import task.TaskParser;
import ui.TextUi;

/**
 * Handles incorrect commands.
 */
public class IncorrectCommand extends Command {
    /**
     * Executes the command the user provided.
     * Since it is not a valid command, an error message is printed instead.
     *
     * @param taskParser The task parser that Duke use to determine what task to execute.
     * @param ui         The Ui instance. Use to display messages to users.
     * @param storage    The storage instance. Use to write data into the text file.
     */
    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        ui.printMessage(Messages.MESSAGE_VALID_COMMAND_LIST);
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
