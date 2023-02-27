package commands;

import storage.Storage;
import task.TaskParser;
import ui.TextUi;

/**
 * Executes the unmark command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int taskNumberInList;

    /**
     * A constructor that accepts the task number specified by the user.
     *
     * @param taskNumberInList The task number specified.
     */
    public UnmarkCommand(int taskNumberInList) {
        this.taskNumberInList = taskNumberInList;
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
        taskParser.unmarkTask(ui, storage, taskNumberInList);
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
