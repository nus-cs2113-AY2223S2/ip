package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.ui.Ui;

/**
 * Command to exit the program
 */
public class ExitCommand extends Command{

        public ExitCommand() {
            super(true);
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showGoodbye();
        }
}
