package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

/**
 * Handles the find command.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String findKeyword;

    /**
     * A constructor that accepts the keyword the user specified.
     *
     * @param findKeyword The keyword the user specified.
     */
    public FindCommand(String findKeyword) {
        this.findKeyword = findKeyword;
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
        taskParser.findTask(ui, findKeyword);
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
