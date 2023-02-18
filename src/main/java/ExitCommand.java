/**
 * Represents a command to save the current list of tracked tasks and exit from
 * the Duke ChatBot.
 */
public class ExitCommand extends Command {
    public ExitCommand(String firstWord, String restOfCommand) {
        super(firstWord, restOfCommand);
    }

    /**
     * Save the current list of tracked tasks to tasks.txt file.
     * @param taskList TaskList object containing the array of tracked tasks
     * @param ui Ui object containing methods for user interaction
     * @param storage Storage object for dealing with saving tasks to file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList, ui);
    }

    /**
     * Returns true to tell the Duke ChatBot to stop prompting user for commands and exit.
     * @return True to stop prompting user for commands
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
