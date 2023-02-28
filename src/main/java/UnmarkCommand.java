/**
 * Represents a command to unmark a task in the current list of tracked tasks.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand(String firstWord, String restOfCommand) {
        super(firstWord, restOfCommand);
    }

    /**
     * Mark the task whose index is specified in restOfCommand as not done.
     * @param taskList TaskList object containing the array of tracked tasks
     * @param ui Ui object containing methods for user interaction
     * @param storage Storage object for dealing with saving tasks to file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Extract the index of the task to be unmarked
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(restOfCommand) - 1;
        } catch (NumberFormatException e) {
            ui.showUnmarkError();
            return;
        }

        try {
            taskList.unmarkTask(taskIndex);
            ui.showTaskIsUnmarked();
            ui.showCurrTask(taskList, taskIndex);
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidTaskNumberError();
        }
    }
}
