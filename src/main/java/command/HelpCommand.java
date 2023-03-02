package command;

import task.TaskList;
import ui.Ui;
import utils.Storage;

/**
 *  Command for help.
 *  Format: help
 */
public class HelpCommand extends Command{
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
