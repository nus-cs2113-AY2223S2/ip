package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printHelpMessage();
    }
}
