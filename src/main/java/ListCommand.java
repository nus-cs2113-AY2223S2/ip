/**
 * Represents a command to view the whole list of currently tracked tasks.
 */
public class ListCommand extends Command {
    public ListCommand(String firstWord, String restOfCommand) {
        super(firstWord, restOfCommand);
    }

    /**
     * Prints the whole list of tasks which are currently tracked.
     * @param taskList TaskList object containing the array of tracked tasks
     * @param ui Ui object containing methods for user interaction
     * @param storage Storage object for dealing with saving tasks to file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // There are no tasks in the list
        if (taskList.numOfTasks == 0) {
            ui.showEmptyList();
            return;
        }

        String output = "\tHere are the tasks in your list:" + System.lineSeparator();
        for (int i = 0; i < taskList.numOfTasks; i++) {
            output += "\t" + (i + 1) + "." + taskList.tasks.get(i) + System.lineSeparator();
        }
        System.out.print(output);
    }
}
