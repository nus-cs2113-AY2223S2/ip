/**
 * Represents a command to find tasks which contains a certain keyword
 * from the list of currently tracked tasks.
 */
public class FindCommand extends Command {
    public FindCommand(String firstWord, String restOfCommand) {
        super(firstWord, restOfCommand);
    }

    /**
     * Prints the list of tasks whose description matches the keyword.
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

        // No keyword is specified in find command
        if (restOfCommand.length() == 0) {
            ui.showFindError();
            return;
        }

        int matchingTaskCount = 1;
        String output = "\tHere are the matching tasks in your list:" + System.lineSeparator();
        for (int i = 0; i < taskList.numOfTasks; i++) {
            String keyword = restOfCommand.toLowerCase();
            String taskDescription = taskList.tasks.get(i).description.toLowerCase();
            if (taskDescription.contains(keyword)) {
                output += "\t" + (matchingTaskCount) + "." + taskList.tasks.get(i)
                        + System.lineSeparator();
                matchingTaskCount++;
            }
        }

        if (matchingTaskCount == 1) {
            ui.showNoMatches();
        } else {
            System.out.print(output);
        }
    }
}