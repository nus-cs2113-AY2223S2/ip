package command;

import storage.TaskStorage;
import task.TaskList;
import ui.UI;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, TaskStorage storage, UI ui) {
        ui.printBye();
    }
}
