/**
 * Represents a command to do something with the current list of tracked tasks.
 */
public class Command {
    protected String firstWord;
    protected String restOfCommand;

    public Command(String firstWord, String restOfCommand) {
        this.firstWord = firstWord;
        this.restOfCommand = restOfCommand;
    }

    /**
     * Execute the command depending on the type of command.
     * @param taskList TaskList object containing the array of tracked tasks
     * @param ui Ui object containing methods for user interaction
     * @param storage Storage object for dealing with saving tasks to file
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
    }

    /**
     * Tells the Duke ChatBot whether to continue prompting user for commands, depending
     * on the return value where false means continue prompting and true means stop.
     * @return False by default. Only return true when encounter "bye" command
     */
    public boolean shouldExit() {
        return false;
    }
}
