package duke;

public class ExitCommand extends Command {
    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        this.setExit();
    }
}
