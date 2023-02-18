/**
 * Represents a command to delete a task from the current list of tracked tasks.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String firstWord, String restOfCommand) {
        super(firstWord, restOfCommand);
    }

    /**
     * Delete the task whose index is specified in restOfCommand from the list.
     * @param taskList TaskList object containing the array of tracked tasks
     * @param ui Ui object containing methods for user interaction
     * @param storage Storage object for dealing with saving tasks to file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Extract the index of the task to be deleted
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(restOfCommand) - 1;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            ui.showDeleteError();
            return;
        }

        String output = "";
        try {
            output += ui.getTaskIsDeleted() + ui.getCurrTask(taskList, taskIndex);
            taskList.deleteTask(taskIndex);
            taskList.decrementNumOfTasks();
            System.out.println(output);
            ui.showCurrNumOfTask(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidTaskNumberError();
        }
    }
}
