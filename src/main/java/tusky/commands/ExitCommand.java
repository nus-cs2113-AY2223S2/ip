package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.ui.Ui;

public class ExitCommand extends Command{

        public ExitCommand() {
            super(true);
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showGoodbye();
        }
}
