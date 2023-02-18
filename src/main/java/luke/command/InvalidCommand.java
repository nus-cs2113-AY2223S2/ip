package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.AddTaskException;

public class InvalidCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printInvalidCommand();
    }
}
