package duke;

public class HelpCommand extends Command {
    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printHelpMessage();
    }
}
