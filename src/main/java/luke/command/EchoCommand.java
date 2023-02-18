package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

public class EchoCommand extends Command {
    private String fullCommand;

    public EchoCommand (String fullCommand) {
        this.fullCommand = fullCommand;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString(this.fullCommand);
    }
}
