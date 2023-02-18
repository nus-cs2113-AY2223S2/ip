public class Command {
    protected String firstWord;
    protected String restOfCommand;

    public Command(String firstWord, String restOfCommand) {
        this.firstWord = firstWord;
        this.restOfCommand = restOfCommand;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
    }

    public boolean shouldExit() {
        return false;
    }
}
