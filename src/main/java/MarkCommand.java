/**
 * Represents a command to mark a task in the current list of tracked tasks.
 */
public class MarkCommand extends Command {
    public MarkCommand(String firstWord, String restOfCommand) {
        super(firstWord, restOfCommand);
    }

    /**
     * Mark the task whose index is specified in restOfCommand as done.
     * @param taskList TaskList object containing the array of tracked tasks
     * @param ui Ui object containing methods for user interaction
     * @param storage Storage object for dealing with saving tasks to file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Extract the index of the task to be marked
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(restOfCommand) - 1;
        } catch (NumberFormatException e) {
            ui.showMarkError();
            return;
        }

        try {
            taskList.markTask(taskIndex);
            ui.showTaskIsMarked();
            ui.showCurrTask(taskList, taskIndex);
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidTaskNumberError();
        }
    }
}
