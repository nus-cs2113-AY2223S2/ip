package Command;

import Entities.Task;
import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

public class AddCommand extends Command {
    private Task addedTask;

    public AddCommand(Task addedTask) {
        this.addedTask = addedTask;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        tasks.addTask(addedTask);
        ui.taskAddedMessage(addedTask, tasks.getTasksCount());
        storage.write(tasks);
    }
}
