public class ExitCommand extends Command {
    public ExitCommand(String firstWord, String restOfCommand) {
        super(firstWord, restOfCommand);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList, ui);
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
