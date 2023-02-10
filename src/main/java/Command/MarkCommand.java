package Command;

import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printMarkedTask(tasks.markTask(taskIndex));
    }
}
